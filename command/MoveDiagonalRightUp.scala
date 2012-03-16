package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalRightUp(n : Int, l : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalRightUp(n, l) 
	}
}