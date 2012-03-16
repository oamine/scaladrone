package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalForwardUp(n : Int, l : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalForwardUp(n, l) 
	}
}