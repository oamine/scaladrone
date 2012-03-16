package command

import scaladrone.Drone
import scala.actors.Actor

case class speedVerticalIndoor extends Command {
	def execute(d : Drone){
		d.speedVerticalIndoor 
	}
}