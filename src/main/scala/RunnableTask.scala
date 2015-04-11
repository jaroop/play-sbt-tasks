package com.jaroop.play.sbt

import play.api.Play
import play.core.StaticApplication

/** An abstract `Runnable` class that must implement an `execute` function that returns `Unit`.
 *  @param arguments Arguments that can be passed to the task.
 */
abstract class RunnableTask(arguments: String) extends Runnable {

    /** The task method to run inside of an ad-hoc Play application. */
    def execute(): Unit

    /** Run the task within a static Play application, and shut it down when finished. */
    def run(): Unit = {
        val application: StaticApplication = new StaticApplication(new java.io.File("."))
        try { this.execute() } finally { Play.stop() }
    }

}
