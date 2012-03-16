package command

import scaladrone.Drone
import scala.actors.Actor

case class notify extends Command {
	def execute(d : Drone){
		d.notify 
	}
}