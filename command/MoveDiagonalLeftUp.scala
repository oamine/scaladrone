package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalLeftUp(n : Int, l : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalLeftUp(n, l) 
	}
}