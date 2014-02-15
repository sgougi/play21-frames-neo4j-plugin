import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

	val appName          = "play21-frames-neo4j-plugin"
	val appVersion       = "1.2"
	val tinkerpopVersion = "2.5.0-SNAPSHOT"	  
	val neo4jVersion     = "2.0.1"
	  
    val appDependencies = Seq(
      "com.wingnest.play2" % "play21-frames-module_2.10" % "2.5.0-SNAPSHOT",
      ("org.neo4j" % "neo4j" % {neo4jVersion} ) excludeAll(
          ExclusionRule(organization="org.neo4j", artifact="neo4j-udc")
      ), 
      ("org.neo4j" % "neo4j-shell" % {neo4jVersion} ),
      ("org.neo4j" % "neo4j-cypher" % {neo4jVersion} ),
      ("org.neo4j" % "neo4j-kernel" % {neo4jVersion} ),
      ("org.neo4j" % "neo4j-graphviz" % {neo4jVersion} ),
      ("org.neo4j" % "neo4j-udc" % {neo4jVersion} ),
      ("com.tinkerpop.blueprints" % "blueprints-neo4j2-graph" % {tinkerpopVersion}) excludeAll(
          ExclusionRule(organization="org.neo4j")
      ) 
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(		
      // Add your own project settings here
      publishArtifact in(Compile, packageDoc) := false,
      organization := "com.wingnest.play2",
      libraryDependencies ++= Seq(
      ),
      resolvers ++= Seq(
        //"maven-central" at "http://repo1.maven.org/maven2",
        "neo4j-public-repository" at "http://m2.neo4j.org/content/groups/public"
      )
    )

}
