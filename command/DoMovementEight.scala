package command

import scaladrone.Drone
import scala.actors.Actor

case class doMovementEight(l : Float) extends Command {
	def execute(d : Drone){
		d.doMovementEight(l) 
	}
}