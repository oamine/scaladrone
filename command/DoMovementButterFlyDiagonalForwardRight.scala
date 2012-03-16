package command

import scaladrone.Drone
import scala.actors.Actor

case class doMovementButterFlyDiagonalForwardRight(n : Int, l : Float) extends Command {
	def execute(d : Drone){
		d.doMovementButterFlyDiagonalForwardRight(n, l) 
	}
}