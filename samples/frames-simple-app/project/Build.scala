import sbt._
import Keys._
import play.Project._


object ApplicationBuild extends Build {

    val appName         = "play21-frames-neo4j-simple-app"
    val appVersion      = "1.2"
    
    val appDependencies = Seq(
	    "com.wingnest.play2" % "play21-frames-neo4j-plugin_2.10" % "1.2" 
	    	exclude("org.scala-lang", "scala-library"), // https://github.com/webjars/webjars-play/issues/27 
        javaCore
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here
        resolvers ++= Seq(
        //"maven-central" at "http://repo1.maven.org/maven2",
        "neo4j-public-repository" at "http://m2.neo4j.org/content/groups/public"
      )
    )
  
}
