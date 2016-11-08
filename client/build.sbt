name := """akkademy-db-client"""

version := "1.0"

scalaVersion := "2.11.8"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "com.akkademy" %% "akkademy-common" % "0.0.1-SNAPSHOT",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

