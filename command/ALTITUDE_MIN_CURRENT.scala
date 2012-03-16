package command

import scaladrone.Drone
import scala.actors.Actor

case class ALTITUDE_MIN_CURRENT extends Command {
	def execute(d : Drone){
		d.ALTITUDE_MIN_CURRENT 
	}
}