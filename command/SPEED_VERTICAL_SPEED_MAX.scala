package command

import scaladrone.Drone
import scala.actors.Actor

case class SPEED_VERTICAL_SPEED_MAX extends Command {
	def execute(d : Drone){
		d.SPEED_VERTICAL_SPEED_MAX 
	}
}