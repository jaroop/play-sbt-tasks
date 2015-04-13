*Play sbt tasks*
------------------------------------------------
A very simple SBT plugin for creating tasks that require a Play application to run one-off tasks within the sbt/activator console.

Installation:
-------------
Play SBT tasks is available on maven central. Just add the following to project/plugins.sbt:

``` scala
addSbtPlugin("com.jaroop" %% "play-sbt-tasks-plugin" % "1.0.3")
```

and Build.scala, or build.sbt:

``` scala
libraryDependencies += "com.jaroop" %% "play-sbt-tasks" % "1.0.3"
```

Usage
-----

To create a new task, extend the abstract class `RunnableTask`, and implement the `execute` method:

``` scala
package com.example

class MyTask(args: String) extends RunnableTask(args) {
	
	def execute: Unit = println("This is my new task.. it doesn't really do anything.")

}
```

Then register the task in Play's Build.scala:

``` scala
import com.jaroop.play.sbt.Task

...

  val main = play.Project(appName, appVersion, appDependencies).settings(
      Task.register("myTask", "com.example.MyTask", "Displays a test message.")
  )
```

Or build.sbt:

``` scala
import com.jaroop.play.sbt.Task

Task.register("myTask", "com.example.MyTask", "Displays a test message.")
```