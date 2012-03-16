package command

import scaladrone.Drone
import scala.actors.Actor

case class toString extends Command {
	def execute(d : Drone){
		d.toString 
	}
}