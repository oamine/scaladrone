package command

import scaladrone.Drone
import scala.actors.Actor

case class mode extends Command {
	def execute(d : Drone){
		d.mode 
	}
}