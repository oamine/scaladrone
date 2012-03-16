package command

import scaladrone.Drone
import scala.actors.Actor

case class isVideoEnabled_$eq(o : Boolean) extends Command {
	def execute(d : Drone){
		d.isVideoEnabled_$eq(o) 
	}
}