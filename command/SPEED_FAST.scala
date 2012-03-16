package command

import scaladrone.Drone
import scala.actors.Actor

case class SPEED_FAST extends Command {
	def execute(d : Drone){
		d.SPEED_FAST 
	}
}