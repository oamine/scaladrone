package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalRightForward(l : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalRightForward(l, f) 
	}
}