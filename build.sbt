name := """test-reactive-platform"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

overrideRpScalaVersion := true

routesGenerator := InjectedRoutesGenerator

wartremoverErrors += PlayWart.FutureObject