package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalLeftForwardTime(n : Int, l : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalLeftForwardTime(n, l) 
	}
}