*Play sbt tasks*
------------------------------------------------
A very simple SBT plugin for tasks runnable from the Play sbt console

Installation:
-------------
Add the following to project/plugins.sbt:

``` scala
resolvers += Resolver.url("Jaroop Releases", url("https://jaroop-releases.s3.amazonaws.com"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.jaroop" %% "play-sbt-tasks" % "1.0.0")
```

and Build.scala, or build.sbt:

``` scala
libraryDependencies += Defaults.sbtPluginExtra("com.jaroop" % "play-sbt-tasks" % "1.0.0", "0.13", "2.10")
```

Usage
-----

To create a new task, extend the abstract class `Task`, and implement `run`:

``` scala
package com.example

class MyTask(args: String) extends Task(args) {
	
	def run: Unit = println("This is my new task.. it doesn't really do anything.")

}
```

Then register the task in Play's Build.scala:

``` scala
import com.jaroop.play.sbt.Task

...

  val main = play.Project(appName, appVersion, appDependencies).settings(
      Task.register("myTask","com.example.MyTask","Displays a test message.")
  )
```

Or build.sbt:

``` scala
import com.jaroop.play.sbt.Task

Task.register("myTask","com.example.MyTask","Displays a test message.")
```