package command

import scaladrone.Drone
import scala.actors.Actor

case class land extends Command {
	def execute(d : Drone){
		d.land 
	}
}