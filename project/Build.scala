import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

	val appName         = "play21-frames-neo4j-plugin"
	val appVersion      = "1.0"
	val tinkerpopVersion = "2.4.0-SNAPSHOT"	  

	val neo4jVersion    = "1.9.1"
	val jerseyVersion   = "1.9"
	  
    val appDependencies = Seq(
      "com.wingnest.play2" % "play21-frames-module_2.10" % "2.4.0-SNAPSHOT-1.1",
      "org.neo4j" % "neo4j" % {neo4jVersion} force(),
      "org.neo4j" % "neo4j-ha" % {neo4jVersion} force(),
      "org.neo4j" % "neo4j-backup" % {neo4jVersion} force(),
      "org.neo4j" % "neo4j-com" % {neo4jVersion} force(),
      "org.neo4j" % "neo4j-cluster" % {neo4jVersion} force(),
      "org.neo4j" % "neo4j-consistency-check" % {neo4jVersion} force(),
      "org.neo4j" % "neo4j-management" % {neo4jVersion} force(),
      ("com.tinkerpop.blueprints" % "blueprints-neo4j-graph" % {tinkerpopVersion})
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(		
      // Add your own project settings here
      publishArtifact in(Compile, packageDoc) := false,
      organization := "com.wingnest.play2",
      libraryDependencies ++= Seq(
        "org.neo4j.app" % "neo4j-server" % {neo4jVersion} classifier "static-web" classifier "",
        "com.sun.jersey" % "jersey-core" % {jerseyVersion},
		"ch.qos.logback" % "logback-core" % "1.0.3" force(),
		"ch.qos.logback" % "logback-classic" % "1.0.3" force()
      ),

      resolvers ++= Seq(
        //"maven-central" at "http://repo1.maven.org/maven2",
        "neo4j-public-repository" at "http://m2.neo4j.org/content/groups/public"
      )
    )

/*
	val neo4jVersion    = "1.8"
	val jerseyVersion   = "1.9"
	  
    val appDependencies = Seq(
      "com.wingnest.play2" % "play21-frames-module_2.10" % "1.0-SNAPSHOT",
      "org.neo4j" % "neo4j" % {neo4jVersion} force(),
      "org.neo4j" % "neo4j-ha" % {neo4jVersion} force(),
      "org.neo4j" % "neo4j-backup" % {neo4jVersion} force(),
      "org.neo4j" % "neo4j-com" % {neo4jVersion} force(),
      "org.neo4j" % "neo4j-management" % {neo4jVersion} force(),
      ("com.tinkerpop.blueprints" % "blueprints-neo4j-graph" % {tinkerpopVersion})
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(		
      // Add your own project settings here
      publishArtifact in(Compile, packageDoc) := false,
      libraryDependencies ++= Seq(
        "org.neo4j.app" % "neo4j-server" % {neo4jVersion} classifier "static-web" classifier "",
        "com.sun.jersey" % "jersey-core" % {jerseyVersion},
		"ch.qos.logback" % "logback-core" % "1.0.3" force(),
		"ch.qos.logback" % "logback-classic" % "1.0.3" force()
      ),

      resolvers ++= Seq(
        //"maven-central" at "http://repo1.maven.org/maven2",
        "neo4j-public-repository" at "http://m2.neo4j.org/content/groups/public"
      )
    )
*/    
}
