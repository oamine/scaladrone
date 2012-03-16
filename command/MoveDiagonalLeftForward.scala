package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalLeftForward(l : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalLeftForward(l, f) 
	}
}