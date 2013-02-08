import sbt._
import Keys._
import play.Project._


object ApplicationBuild extends Build {

    val appName         = "play21-frames-neo4j-simple-app"
    val appVersion      = "1.0-SNAPSHOT"
    val neo4jVersion    = "1.9.M04"
  
    val appDependencies = Seq(
	    "com.wingnest.play2" % "play21-frames-neo4j-plugin_2.10" % "1.0-SNAPSHOT",
        "ch.qos.logback" % "logback-core" % "1.0.3" force(),
        "ch.qos.logback" % "logback-classic" % "1.0.3" force(),
        javaCore
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here      
    )

}
