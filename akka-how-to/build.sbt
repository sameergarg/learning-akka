name := "akka-how-to"

version := "1.0"

scalaVersion := "2.11.8"

/**
  * By default, sbt buffers log output for each suite until all tests for that suite complete and causing "spurty" output. We recommend you disable sbt's log buffering so you can enjoy ScalaTest's built-in event buffering algorithm, which shows the events of one suite as they occur until that suite either completes or a timeout occurs, at which point ScalaTest switches a different suite's events
  */
logBuffered in Test := false

libraryDependencies +=
  "com.typesafe.akka" %% "akka-actor" % "2.4.10"

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-testkit_2.11
libraryDependencies += "com.typesafe.akka" % "akka-testkit_2.11" % "2.4.11"

// https://mvnrepository.com/artifact/org.scalatest/scalatest_2.11
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.0" % "test"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
//for scala test nclude the SuperSafe Community Edition Scala compiler plugin, which will flag errors in your ScalaTest (and Scalactic) code at compile time,
resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"