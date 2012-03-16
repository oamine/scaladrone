package command

import scaladrone.Drone
import scala.actors.Actor

case class ready extends Command {
	def execute(d : Drone){
		d.ready 
	}
}