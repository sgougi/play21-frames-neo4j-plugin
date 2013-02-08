import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

	val appName         = "play21-frames-neo4j-plugin"
	val appVersion      = "1.0-SNAPSHOT"
	val neo4jVersion    = "1.9.M04"
	val tinkerpopVersion = "2.2.0"  

	val appDependencies = Seq(
		"com.wingnest.play2" % "play21-frames-module_2.10" % "1.0-SNAPSHOT",
	
		
		"org.neo4j" % "neo4j-community" % {neo4jVersion} force(),
		"org.neo4j.app" % "neo4j-server" % {neo4jVersion} force(),

		"org.neo4j" % "neo4j-backup" % {neo4jVersion} force(),
		"org.neo4j" % "neo4j-com" % {neo4jVersion} force(),
		"org.neo4j" % "neo4j-cypher" % {neo4jVersion} force(),
		"org.neo4j" % "neo4j-graph-algo" % {neo4jVersion} force(),
		"org.neo4j" % "neo4j-graph-matching" % {neo4jVersion} force(),
		"org.neo4j" % "neo4j-ha" % {neo4jVersion} force(), 
		"org.neo4j" % "neo4j-jmx" % {neo4jVersion} force(),
		"org.neo4j" % "neo4j-kernel" % {neo4jVersion} force(),
		"org.neo4j" % "neo4j-lucene-index" % {neo4jVersion} force(),
		"org.neo4j" % "neo4j-management" % {neo4jVersion} force(),
		"org.neo4j" % "neo4j-shell" % {neo4jVersion} force(),
		"org.neo4j" % "neo4j-udc" % {neo4jVersion} force(),
		"org.neo4j" % "server-api" % {neo4jVersion} force(),
		
		"org.neo4j" % "neo4j-community" % {neo4jVersion} force(),
		"org.neo4j.app" % "neo4j-server" % {neo4jVersion} force(),
		"org.neo4j.server.plugin" % "neo4j-gremlin-plugin" % {neo4jVersion} force(),
		"org.neo4j.server.plugin" % "neo4j-cypher-plugin" % {neo4jVersion} force(),
		
		("com.tinkerpop.blueprints" % "blueprints-neo4j-graph" % {tinkerpopVersion}),

//		("com.sun.jersey" % "jersey-servlet" % "1.17"),
		"com.sun.jersey" % "jersey-core" % "1.9" force(),
		"com.sun.jersey.contribs" % "jersey-multipart" % "1.9" force(),
		("janino" % "janino" % "2.5.10"),

		"ch.qos.logback" % "logback-core" % "1.0.3" force(),
		"ch.qos.logback" % "logback-classic" % "1.0.3" force(),
		
		("org.neo4j.app" % "neo4j-server-1.9.M04-static-web" % {neo4jVersion} 
           from  "http://m2.neo4j.org/releases/org/neo4j/app/neo4j-server/" + {neo4jVersion} + "/neo4j-server-" + {neo4jVersion} + "-static-web.jar")
	)

	val main = play.Project(appName, appVersion, appDependencies).settings(		
	    organization := "com.wingnest.play2",
	    resolvers += "Oracle Releases" at "http://download.oracle.com/maven/",
	    resolvers += "JBOSS" at "https://repository.jboss.org/nexus/content/repositories/public/"
	)
	
}
