package command

import scaladrone.Drone
import scala.actors.Actor

case class ALTITUDE_MAX_CURRENT extends Command {
	def execute(d : Drone){
		d.ALTITUDE_MAX_CURRENT 
	}
}