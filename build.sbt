
lazy val MetaCogUI = project.in(file("."))
  .settings(
    name := "MetaCogUI",
    version := "2.0.0",
    scalaVersion := "2.12.6",
    libraryDependencies += "com.thoughtworks.binding" %%% "dom" % "11.0.1",
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
    crossTarget in fastOptJS := baseDirectory.value / "js"
  ).enablePlugins(ScalaJSPlugin)