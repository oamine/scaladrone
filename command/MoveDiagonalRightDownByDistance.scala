package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalRightDownByDistance(l : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalRightDownByDistance(l, f) 
	}
}