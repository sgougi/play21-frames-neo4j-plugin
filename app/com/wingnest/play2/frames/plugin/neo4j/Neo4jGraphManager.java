/*
 * Copyright since 2013 Shigeru GOUGI
 *                              e-mail:  sgougi@gmail.com
 *                              twitter: @igerugo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wingnest.play2.frames.plugin.neo4j;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.GraphDatabaseAPI;
import org.neo4j.server.WrappingNeoServerBootstrapper;
import org.neo4j.server.configuration.ServerConfigurator;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph;
import com.wingnest.play2.frames.plugin.FramesLogger;
import com.wingnest.play2.frames.plugin.graphManager.AbstractGraphManager;
import com.wingnest.play2.frames.plugin.neo4j.utils.ApplicationConfUtils;

public class Neo4jGraphManager extends AbstractGraphManager {

	private static volatile WrappingNeoServerBootstrapper bootstrapperDb = null;
	
	final private Neo4jGraph graph;
	
	public Neo4jGraphManager() {
		FramesLogger.info("Crate Neo4jGraphManager");
		graph = createGraph();
	}
	
	@Override
	public <T extends Graph> T getGraph() {
		return (T)graph;
	}

	@Override
	public void stopTransaction(final Conclusion conclusion) {
		graph.stopTransaction(conclusion);
	}
	
	private Neo4jGraph createGraph() {
		final Properties prop = ApplicationConfUtils.loadProperties(ApplicationConfUtils.getNeo4jProperties());
		final Properties serverProp = ApplicationConfUtils.loadProperties(ApplicationConfUtils.getNeo4jServerProperties());
		
		final Map<String, String> config = new HashMap(prop);		
		final GraphDatabaseAPI graphdb = (GraphDatabaseAPI) new GraphDatabaseFactory()
			.newEmbeddedDatabaseBuilder(serverProp.getProperty("org.neo4j.server.database.location"))
			.setConfig(config)
			.newGraphDatabase();

		final Neo4jGraph graph = new Neo4jGraph(graphdb);
		
		if(ApplicationConfUtils.isEnableWebServer()) {
			final ServerConfigurator sconfig = new ServerConfigurator(graphdb);

			for ( Object key : serverProp.keySet() ) {
				sconfig.configuration().setProperty(key.toString(), serverProp.get(key));
			}			
			bootstrapperDb = new WrappingNeoServerBootstrapper(graphdb, sconfig);
			bootstrapperDb.start();
		}

		return graph;
	}

	@Override
	public void onShutdown() {
		if(bootstrapperDb != null)
			bootstrapperDb.stop();
		bootstrapperDb = null;		
		if(graph != null)
			graph.shutdown();
	}	

}
