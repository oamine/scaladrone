package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalForwardDownByDistance(l : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalForwardDownByDistance(l, f) 
	}
}