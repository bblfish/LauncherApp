name := "LauncherApp"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies +=  "org.scala-js" %%% "scalajs-dom" % "0.9.7"

scalaJSUseMainModuleInitializer := false

//mainClass := Some("launcher.LauncherApp")

enablePlugins(ScalaJSPlugin)
