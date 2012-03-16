package command

import scaladrone.Drone
import scala.actors.Actor

case class getSpeedVerticalIndoor extends Command {
	def execute(d : Drone){
		d.getSpeedVerticalIndoor 
	}
}