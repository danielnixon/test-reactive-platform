import sbt.Keys._
import sbt._

name := """test-reactive-platform"""

version := "1.0-SNAPSHOT"

lazy val Lint = sbt.config("lint") extend Test

scalaVersion := "2.11.8"

overrideRpScalaVersion := true

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .configs(Lint)
  .settings(settings)
  .settings(inConfig(Lint) { settings ++ lintSettings })

lazy val settings = Seq(
  scalacOptions in Compile := (scalacOptions in Compile).value filterNot { _ contains "wartremover" },
  scalacOptions in Test := (scalacOptions in Test).value filterNot { _ contains "wartremover" },
  routesGenerator := InjectedRoutesGenerator
)

lazy val lintSettings =
  Defaults.compileSettings ++
  Seq(
    sources in Lint := (sources in Lint).value ++ (sources in Compile).value ++ (sources in Test).value,
    wartremoverWarnings ++= Seq(
      Wart.custom("org.danielnixon.playwarts.AkkaObject")
    )
  )

