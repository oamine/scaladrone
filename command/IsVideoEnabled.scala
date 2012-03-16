package command

import scaladrone.Drone
import scala.actors.Actor

case class isVideoEnabled extends Command {
	def execute(d : Drone){
		d.isVideoEnabled 
	}
}