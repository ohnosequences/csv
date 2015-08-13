Nice.scalaProject

name          := "csv"
organization  := "ohnosequences"
description   := "csv project"

bucketSuffix  := "era7.com"

libraryDependencies ++= Seq(
  "ohnosequences" %% "cosas"      % "0.6.0"
  "org.scalatest" %% "scalatest"  % "2.2.5" % Test
)
