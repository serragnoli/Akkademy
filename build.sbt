name := """akkademy-db"""
organization := "com.akkademy-db"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.0"

// Change this to another test framework if you prefer
// libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.12",
  "com.typesafe.akka" %% "akka-remote" % "2.4.12",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.12",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

mappings in(Compile, packageBin) ~= {
  _.filterNot {
    case (_, myName) => Seq("application.conf").contains(myName)
  }
}
