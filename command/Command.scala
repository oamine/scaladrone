package command

import scaladrone.Drone
import scala.actors.Actor

trait  Command  {
	def execute (d: Drone)
}