name := "stand-alone-wsclient"

organization  := "com.example"

version       := "0.1"

scalaVersion  := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "com.typesafe.play"       %% "play-ws"                    % "2.5.9",
    "io.spray"                %%  "spray-json"                % "1.3.2",
    "io.spray"                %%  "spray-can"                 % sprayV,
    "io.spray"                %%  "spray-routing"             % sprayV,
    "com.typesafe.akka"       %%  "akka-actor"                % akkaV,
    "org.scalactic"           %%  "scalactic"                 % "3.0.0",
    "ch.qos.logback"          %   "logback-classic"           % "1.1.7",
    "com.typesafe.scala-logging" %% "scala-logging"           % "3.5.0",
    "org.scalatest"           %% "scalatest"                  % "3.0.0"   % "test",
    "com.typesafe.akka"       %%  "akka-testkit"              % akkaV     % "test",
    "io.spray"                %%  "spray-testkit"             % sprayV    % "test"
  )
}

resolvers += "softprops-maven" at "http://dl.bintray.com/content/softprops/maven"
resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"