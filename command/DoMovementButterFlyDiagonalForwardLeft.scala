package command

import scaladrone.Drone
import scala.actors.Actor

case class doMovementButterFlyDiagonalForwardLeft(n : Int, l : Float) extends Command {
	def execute(d : Drone){
		d.doMovementButterFlyDiagonalForwardLeft(n, l) 
	}
}