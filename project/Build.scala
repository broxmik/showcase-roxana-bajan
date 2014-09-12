import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "showcase"
  val appVersion      = "1.0.0"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "org.springframework" % "spring-beans" % "1.2.6",
    "com.google.inject" % "guice" % "3.0",
    "com.googlecode.lambdaj" % "lambdaj" % "2.3.3"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
  )

}
