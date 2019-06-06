//enablePlugins(JavaAppPackaging)

organization := "ch.unige"

name := "GBF"

version := "0.1.1"

scalaVersion := "2.12.1"


libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.5" % "test",
  "com.typesafe.akka" %%  "akka-actor" % "2.4.17",
  "com.typesafe.akka" %% "akka-stream" % "2.4.17"
)

fork in run := true

javaOptions in run += "-Xmx2G"


scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xlint", "-Xfatal-warnings" )

scalaSource in Compile <<= baseDirectory(_ / "src")

javaSource in Compile <<= baseDirectory(_ / "java" / "src" )

scalaSource in Test <<= baseDirectory(_ / "test")

resourceDirectory in Compile := baseDirectory.value / "resources"
    
seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

// This gets rid of the trailing "-one-jar"

artifact in oneJar <<= moduleName(Artifact(_))

// rename the jar

artifact in oneJar ~= { (art: Artifact) =>
  art.copy(`type` = "jar", extension = "jar", name = art.name + "_FULL")
}

mainClass in oneJar := Some("ch.unige.gbf.conc.ConcMain")
