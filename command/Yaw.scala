package command

import scaladrone.Drone
import scala.actors.Actor

case class yaw extends Command {
	def execute(d : Drone){
		d.yaw 
	}
}