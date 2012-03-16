package command

import scaladrone.Drone
import scala.actors.Actor

case class isFlying extends Command {
	def execute(d : Drone){
		d.isFlying 
	}
}