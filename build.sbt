
lazy val MetaCogUI = project.in(file("."))
  .settings(
    name := "MetaCogUI",
    version := "2.0.0",
    scalaVersion := "2.12.6",
    libraryDependencies ++= Seq(
      //"io.nlytx" %% "nlytx-nlp-api" % "1.1.0",
      //"io.nlytx" %% "nlytx-nlp-expressions" % "1.0.0",
      "com.thoughtworks.binding" %%% "dom" % "11.0.1",
      "com.thoughtworks.binding" %%% "futurebinding" % "11.0.1",
      "fr.hmil" %%% "roshttp" % "2.1.0",
      "com.lihaoyi" %%% "upickle" % "0.6.6"
      //"com.softwaremill.sttp" %%% "core" % "1.2.1"
    ),
    //resolvers += Resolver.bintrayRepo("nlytx", "nlytx-nlp"),
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
    crossTarget in fastOptJS := baseDirectory.value / "assets" / "js",
    crossTarget in fullOptJS := baseDirectory.value / "assets" / "js"
  ).enablePlugins(ScalaJSPlugin)