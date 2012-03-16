package command

import scaladrone.Drone
import scala.actors.Actor

case class speedListInDoor extends Command {
	def execute(d : Drone){
		d.speedListInDoor 
	}
}