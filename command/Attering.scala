package command

import scaladrone.Drone
import scala.actors.Actor

case class attering extends Command {
	def execute(d : Drone){
		d.attering 
	}
}