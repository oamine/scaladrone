package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalForwardUpByDistance(l : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalForwardUpByDistance(l, f) 
	}
}