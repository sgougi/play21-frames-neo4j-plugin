/*
 * Copyright since 2013 Shigeru GOUGI (sgougi@gmail.com)
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

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.GraphDatabaseAPI;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.impls.neo4j2.Neo4j2Graph;
import com.wingnest.play2.frames.plugin.graphManager.AbstractGraphManager;
import com.wingnest.play2.frames.plugin.neo4j.utils.ApplicationConfUtils;
import static com.wingnest.play2.frames.plugin.neo4j.ConfigConsts.*;

public class Neo4jGraphManager extends AbstractGraphManager {

	final private Neo4j2Graph graph;
	
	public Neo4jGraphManager() {
		graph = createGraph();
	}
	
	@Override
	public <T extends Graph> T getGraph() {
		return (T)graph;
	}

	@Deprecated
	public void stopTransaction(final Conclusion conclusion) {
		graph.stopTransaction(conclusion);
	}
	
	@Override
	public void commit() {
		graph.commit();
	}

	@Override
	public void rollback() {
		graph.rollback();
	}	
	
	private Neo4j2Graph createGraph() {
		
		final Properties prop = ApplicationConfUtils.loadProperties(ApplicationConfUtils.getNeo4jProperties());
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final Map<String, String> config = new HashMap(prop);		
		final GraphDatabaseAPI graphdb = (GraphDatabaseAPI) new GraphDatabaseFactory()
			.newEmbeddedDatabaseBuilder(ApplicationConfUtils.getProperty(CONF_NEO4J_DB_PATH, "db"))
			.setConfig(config)
			.newGraphDatabase();

		final Neo4j2Graph graph = new Neo4j2Graph(graphdb);

		return graph;
	}

	@Override
	public void onShutdown() {
		if(graph != null)
			graph.shutdown();
	}	

}
