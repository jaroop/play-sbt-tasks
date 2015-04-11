
lazy val commonSettings = Seq(
    organization := "com.jaroop",
    version := "1.0.3-SNAPSHOT",
    resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    publishMavenStyle := false,
    publishTo <<= (isSnapshot, s3credentials) { 
                    (snapshot,   credentials) => 
      val prefix = if (snapshot) "snapshots" else "releases"
        credentials map S3Resolver(
            name = "Jaroop " + prefix,
            url = "s3://jaroop-" + prefix,
            patterns = Resolver.ivyStylePatterns,
            overwrite = snapshot
        ).toSbtResolver
    }
)

lazy val root = (project in file(".")).
    settings(commonSettings: _*).
    settings(
        name := "play-sbt-tasks",
        crossScalaVersions := Seq("2.11.6", "2.10.5"),
        libraryDependencies ++= Seq(
          "com.typesafe.play" %% "play" % "2.3.8"
        )
    )

lazy val plugin = (project in file("plugin")).
    settings(commonSettings: _*).
    settings(
        name := "play-sbt-tasks-plugin",
        scalaVersion := "2.10.5",
        sbtPlugin := true
    )
