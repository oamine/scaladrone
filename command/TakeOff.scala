package command

import scaladrone.Drone
import scala.actors.Actor

case class takeOff extends Command {
	def execute(d : Drone){
		d.takeOff 
	}
}