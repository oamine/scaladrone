package command

import scaladrone.Drone
import scala.actors.Actor

case class getSpeedVerticalOutdoor extends Command {
	def execute(d : Drone){
		d.getSpeedVerticalOutdoor 
	}
}