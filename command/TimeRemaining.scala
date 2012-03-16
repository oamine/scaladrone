package command

import scaladrone.Drone
import scala.actors.Actor

case class timeRemaining extends Command {
	def execute(d : Drone){
		d.timeRemaining 
	}
}