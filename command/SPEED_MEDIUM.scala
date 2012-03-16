package command

import scaladrone.Drone
import scala.actors.Actor

case class SPEED_MEDIUM extends Command {
	def execute(d : Drone){
		d.SPEED_MEDIUM 
	}
}