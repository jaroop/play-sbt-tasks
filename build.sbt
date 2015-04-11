
lazy val commonSettings = Seq(
    organization := "com.jaroop",
    version := "1.0.3",
    resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    publishMavenStyle := false,
    ***REMOVED***
                    ***REMOVED***
      ***REMOVED***
        ***REMOVED***
            ***REMOVED***
            ***REMOVED***
            ***REMOVED***
            ***REMOVED***
        ***REMOVED***
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
