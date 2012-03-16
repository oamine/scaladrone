package command

import scaladrone.Drone
import scala.actors.Actor

case class isTrimSucceeded extends Command {
	def execute(d : Drone){
		d.isTrimSucceeded 
	}
}