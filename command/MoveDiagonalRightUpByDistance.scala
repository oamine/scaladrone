package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalRightUpByDistance(l : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalRightUpByDistance(l, f) 
	}
}