package command

import scaladrone.Drone
import scala.actors.Actor

case class isFlying_$eq(o : Boolean) extends Command {
	def execute(d : Drone){
		d.isFlying_$eq(o) 
	}
}