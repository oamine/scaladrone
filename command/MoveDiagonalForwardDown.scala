package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalForwardDown(n : Int, l : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalForwardDown(n, l) 
	}
}