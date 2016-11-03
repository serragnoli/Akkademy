name := """akkademy-db"""

version := "1.0"

scalaVersion := "2.12.0"

// Change this to another test framework if you prefer
// libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.12",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.12",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

