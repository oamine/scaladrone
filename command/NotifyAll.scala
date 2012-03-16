package command

import scaladrone.Drone
import scala.actors.Actor

case class notifyAll extends Command {
	def execute(d : Drone){
		d.notifyAll 
	}
}