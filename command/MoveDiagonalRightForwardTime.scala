package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalRightForwardTime(n : Int, l : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalRightForwardTime(n, l) 
	}
}