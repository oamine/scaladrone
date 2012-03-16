package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalLeftDown(n : Int, l : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalLeftDown(n, l) 
	}
}