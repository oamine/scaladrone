package command

import scaladrone.Drone
import scala.actors.Actor

case class isIndoor extends Command {
	def execute(d : Drone){
		d.isIndoor 
	}
}