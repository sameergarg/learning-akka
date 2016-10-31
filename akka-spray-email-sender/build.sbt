organization  := "com.example"

version       := "0.1"

scalaVersion  := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"                %%  "spray-json"    % "1.3.2",
    "io.spray"                %%  "spray-can"     % sprayV,
    "io.spray"                %%  "spray-routing" % sprayV,
    "me.lessis"               %%  "courier"       % "0.1.3",
    "io.spray"                %%  "spray-testkit" % sprayV    % "test",
    "com.typesafe.akka"       %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"       %%  "akka-testkit"  % akkaV     % "test",
    "org.specs2"              %%  "specs2-core"   % "2.3.11"  % "test",
    "org.jvnet.mock-javamail" % "mock-javamail"   % "1.9"     % "test"
  )
}

Revolver.settings

resolvers += "softprops-maven" at "http://dl.bintray.com/content/softprops/maven"
