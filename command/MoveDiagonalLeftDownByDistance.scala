package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalLeftDownByDistance(l : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalLeftDownByDistance(l, f) 
	}
}