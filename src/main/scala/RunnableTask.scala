package com.jaroop.play.sbt

import play.api.{ApplicationLoader, Environment, Mode, Play}

/** An abstract `Runnable` class that must implement an `execute` function that returns `Unit`.
 *  @param arguments Arguments that can be passed to the task.
 */
abstract class RunnableTask(arguments: String) extends Runnable {

    /** The task method to run inside of an ad-hoc Play application. */
    def execute(): Unit

    /** Run the task within an ad-hoc Play application, and shut it down when finished. */
    def run(): Unit = {
        val env = Environment(new java.io.File("."), this.getClass.getClassLoader, Mode.Dev)
        val context = ApplicationLoader.createContext(env)
        val loader = ApplicationLoader(context)
        val app = loader.load(context)
        try { this.execute() } finally { Play.stop(app) }
    }

}
