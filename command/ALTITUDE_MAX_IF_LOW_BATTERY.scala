package command

import scaladrone.Drone
import scala.actors.Actor

case class ALTITUDE_MAX_IF_LOW_BATTERY extends Command {
	def execute(d : Drone){
		d.ALTITUDE_MAX_IF_LOW_BATTERY 
	}
}