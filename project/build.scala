import sbt._
import sbt.Keys._

object CoralFSBuild extends Build {

  lazy val akkaVersion = "2.3.6"
  lazy val sprayVersion = "1.3.1"

  // Settings
  override lazy val settings = super.settings ++ Seq(
    scalacOptions ++= Seq(
      "-deprecation",
      "-unchecked"
    ),
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.11.2"
  )

  // Core
  lazy val core = Project("core", file("core")).
    settings(coreSettings: _*)

  lazy val coreSettings = Seq(
    name := "CoralFS-core",
    libraryDependencies ++= Seq(
      "org.scalatest"              %  "scalatest_2.10" % "2.2.0"     % "test",
      "com.typesafe.akka"          %% "akka-actor"     % akkaVersion,
      "com.typesafe.akka"          %% "akka-testkit"   % akkaVersion % "test",
      "io.spray"                   %% "spray-can"      % sprayVersion,
      "org.scala-lang.modules"     %% "scala-xml"      % "1.0.2"
    )
  )
}
