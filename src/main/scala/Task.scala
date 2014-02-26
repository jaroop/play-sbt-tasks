package com.jaroop.play.sbt

import sbt._
import Keys._
import java.net.URLClassLoader
import play.core.StaticApplication

abstract class Task(arguments: String) extends Runnable {

  val application = new StaticApplication(new java.io.File("."))

}

object Task {

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