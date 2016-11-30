name := "akkademy-db"

// Change this to another test framework if you prefer
// libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-testkit" % "2.4.12",
  "com.akkademy" %% "akkademy-common" % "0.0.2-SNAPSHOT"
)

//mappings in(Compile, packageBin) ~= {
//  _.filterNot {
//    case (_, myName) => Seq("application.conf").contains(myName)
//  }
//}
