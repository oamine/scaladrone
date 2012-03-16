package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalLeftUpByDistance(l : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalLeftUpByDistance(l, f) 
	}
}