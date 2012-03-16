package command

import scaladrone.Drone
import scala.actors.Actor

case class trim extends Command {
	def execute(d : Drone){
		d.trim 
	}
}