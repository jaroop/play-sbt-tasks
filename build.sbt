name := "play-sbt-tasks"

version := "1.0.2"

sbtPlugin := true

scalaVersion := "2.10.4"

organization := "com.jaroop"

libraryDependencies ++= Seq(
  "com.typesafe.play"  %%   "play" % "2.3.0"
)

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

publishMavenStyle := false

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