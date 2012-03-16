package command

import scaladrone.Drone
import scala.actors.Actor

case class isOutdoor extends Command {
	def execute(d : Drone){
		d.isOutdoor 
	}
}