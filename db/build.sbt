name := """akkademy-db"""
organization := "com.akkademy"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.8"

// Change this to another test framework if you prefer
// libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-testkit" % "2.4.12",
  "com.akkademy" %% "akkademy-common" % "0.0.1-SNAPSHOT",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

//mappings in(Compile, packageBin) ~= {
//  _.filterNot {
//    case (_, myName) => Seq("application.conf").contains(myName)
//  }
//}
