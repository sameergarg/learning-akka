organization  := "com.example"

version       := "0.1"

scalaVersion  := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"                %%  "spray-json"                % "1.3.2",
    "io.spray"                %%  "spray-can"                 % sprayV,
    "io.spray"                %%  "spray-client"              % sprayV,
    "io.spray"                %%  "spray-routing"             % sprayV,
    "com.typesafe.akka"       %%  "akka-actor"                % akkaV,
    "com.typesafe.akka"       %%  "akka-testkit"              % akkaV     % "test",
    "org.specs2"              %%  "specs2-core"               % "3.8.6"   % "test",
    "org.specs2"              %%  "specs2-common"             % "3.8.6"   % "test",
    "org.specs2"              %%  "specs2-matcher"            % "3.8.6"   % "test",
    "org.specs2"              %%  "specs2-matcher-extra"      % "3.8.6"   % "test",
    "io.spray"                %%  "spray-testkit"             % sprayV    % "test" exclude("org.specs2", "specs2_2.11")
  )
}

scalacOptions in Test ++= Seq("-Yrangepos")

Revolver.settings

resolvers += "softprops-maven" at "http://dl.bintray.com/content/softprops/maven"
