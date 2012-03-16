package command

import scaladrone.Drone
import scala.actors.Actor

case class getAltitude extends Command {
	def execute(d : Drone){
		d.getAltitude 
	}
}