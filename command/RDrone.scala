package command

import scaladrone.Drone
import scala.actors.Actor

case class rDrone extends Command {
	def execute(d : Drone){
		d.rDrone 
	}
}