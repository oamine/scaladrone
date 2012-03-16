package command

import scaladrone.Drone
import scala.actors.Actor

case class disconnect extends Command {
	def execute(d : Drone){
		d.disconnect 
	}
}