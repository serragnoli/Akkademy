import sbt.Keys._

lazy val commonSettings = Seq(
  organization := "com.akkademy",
  version := "0.0.2-SNAPSHOT",
  scalaVersion := "2.11.8",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.0" % "test"
  )
)

lazy val root = (project in file("."))
  .aggregate(common, db, client, aid)
  .settings(commonSettings: _*)

lazy val common = project
  .settings(commonSettings: _*)

lazy val db = project
  .settings(commonSettings: _*)

lazy val client = project
  .settings(commonSettings: _*)

lazy val aid = project
  .settings(commonSettings: _*)
  .dependsOn(common)
