package command

import scaladrone.Drone
import scala.actors.Actor

case class getYaw extends Command {
	def execute(d : Drone){
		d.getYaw 
	}
}