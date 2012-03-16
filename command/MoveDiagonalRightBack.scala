package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalRightBack(l : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalRightBack(l, f) 
	}
}