package com.jaroop.play.sbt

import sbt._
import Keys._
import java.net.URLClassLoader

object Task {

    /** Creates a runnable play sbt task that can be added to application settings in Build.scala or build.sbt
     *  @param name The name used to invoke the task via sbt.
     *  @param className The fully qualified package/className of the `Runnable` task (e.g. com.jaroop.tasks.MyTask).
     *  @param description A short description of the task shown when running `sbt tasks`.
     *  @return The sbt task to include in sbt settings for an application.
     */
    def register(name: String, className: String, description: String): Setting[InputTask[Unit]] = {
        InputKey[Unit](name, description) <<= inputTask {
            (argTask: TaskKey[Seq[String]]) =>
                (argTask, compile in Compile, dependencyClasspath in Runtime) map { (args, compile, dependencies) =>
                    val classLoader = new URLClassLoader(dependencies.map(_.data.toURI.toURL).toArray, null)
                    val taskClass = classLoader.loadClass(className)
                    val task = taskClass.getConstructor(classOf[String]).newInstance(args.mkString(",")).asInstanceOf[Runnable]
                    task.run()
            }
        }
    }

}
