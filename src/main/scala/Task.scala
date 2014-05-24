package com.jaroop.play.sbt

import sbt._
import Keys._
import java.net.URLClassLoader
import play.core.StaticApplication
import play.api.Play

/** An abstract `Runnable` class` that must implement an `execute` function that returns `Unit`.
* @param arguments Arguments that can be passed to the task.
*/
abstract class Task(arguments: String) extends Runnable {

	/** Abstract function for tasks to run inside of an ad-hoc Play application. */
	def execute(): Unit

	/** Run the task within a static Play application, and shut it down when finished. */
	def run(): Unit = {
		val application: StaticApplication = new StaticApplication(new java.io.File("."))

	    try { this.execute() } finally { Play.stop() }
	}

}

object Task {

	/** Creates a runnable play sbt task that can be added to application settings in Build.scala or build.sbt
	* @param name The name used to invoke the task via sbt.
	* @param className The fully qualified package/className of the `Runnable` task (e.g. com.jaroop.tasks.MyTask).
	* @param description A short description of the task shown when running `sbt tasks`.
	* @return The sbt task to include in sbt settings for an application.
	*/
	def register(name: String, className: String, description: String):Def.Setting[sbt.InputTask[Unit]] = {
    
	    InputKey[Unit](name, description) <<= inputTask {
	        (argTask: TaskKey[Seq[String]]) =>
		        (argTask, compile in Compile, dependencyClasspath in Runtime) map {
		          (args, compile, deps) =>
		                
		        	val depURLs = deps.map(_.data.toURI.toURL).toArray
		        	val classLoader = new URLClassLoader(depURLs, null)
		        	val taskClass = classLoader.loadClass(className)

		       		val task = taskClass.getConstructor(classOf[String]).newInstance(args.mkString(",")).asInstanceOf[Runnable]

		            task.run()

	        }
	    }
  	}

}