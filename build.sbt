import SonatypeKeys._

lazy val commonSettings = Seq(
    organization := "com.jaroop",
    version := "1.0.3",
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
    .settings(sonatypeSettings: _*)
    .settings(commonSettings: _*)
    .settings(
        name := "play-sbt-tasks",
        crossScalaVersions := Seq("2.11.6", "2.10.5"),
        libraryDependencies ++= Seq(
          "com.typesafe.play" %% "play" % "2.3.8"
        )
    )

lazy val plugin = (project in file("plugin"))
    .settings(sonatypeSettings: _*)
    .settings(commonSettings: _*)
    .settings(
        name := "play-sbt-tasks-plugin",
        scalaVersion := "2.10.5",
        sbtPlugin := true
    )
