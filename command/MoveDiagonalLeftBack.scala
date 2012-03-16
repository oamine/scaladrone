package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalLeftBack(l : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalLeftBack(l, f) 
	}
}