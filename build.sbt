name := """akkademy-db-client"""

version := "1.0"

scalaVersion := "2.11.8"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "com.akkademy-db" %% "akkademy-db" % "0.0.1-SNAPSHOT",
  "com.github.romix.akka" %% "akka-kryo-serialization" % "0.5.0",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

