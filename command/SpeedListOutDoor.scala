package command

import scaladrone.Drone
import scala.actors.Actor

case class speedListOutDoor extends Command {
	def execute(d : Drone){
		d.speedListOutDoor 
	}
}