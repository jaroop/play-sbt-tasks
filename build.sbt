lazy val commonSettings = Seq(
    organization := "com.jaroop",
    version := "2.0.0",
    resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo),
    resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    pomExtra := {
      <url>https://github.com/jaroop/play-sbt-tasks</url>
      <licenses>
        <license>
          <name>Apache 2</name>
          <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
        </license>
      </licenses>
      <scm>
        <connection>scm:git:github.com/jaroop/play-sbt-tasks</connection>
        <developerConnection>scm:git:git@github.com:jaroop/play-sbt-tasks</developerConnection>
        <url>github.com/jaroop/play-sbt-tasks</url>
      </scm>
      <developers>
        <developer>
          <id>Jaroop</id>
          <name>Jaroop</name>
          <url>https://jaroop.com</url>
        </developer>
        <developer>
          <id>mz</id>
          <name>Michael Zajac</name>
          <url>https://github.com/mhzajac</url>
        </developer>
      </developers>
    }
)

lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .settings(
    name := "play-sbt-tasks",
    scalaVersion := "2.12.8",
    crossScalaVersions := Seq("2.11.12", "2.12.8"),
    libraryDependencies += "com.typesafe.play" %% "play" % "2.6.+"
  )

lazy val plugin = (project in file("plugin"))
  .enablePlugins(SbtPlugin)
  .settings(commonSettings: _*)
  .settings(
    name := "play-sbt-tasks-plugin",
    scalaVersion := "2.12.8",
  )

scalacOptions ++= Seq(
    "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
    "-encoding", "utf-8",                // Specify character encoding used by source files.
    "-explaintypes",                     // Explain type errors in more detail.
    "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
    "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
    "-Ywarn-inaccessible",               // Warn about inaccessible types in method signatures.
    "-Ywarn-infer-any",                  // Warn when a type argument is inferred to be `Any`.
    "-Ywarn-nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
    "-Ywarn-nullary-unit",               // Warn when nullary methods return Unit.
    "-Ywarn-numeric-widen",              // Warn when numerics are widened.
    "-Ywarn-value-discard"               // Warn when non-Unit expression results are unused.
)
