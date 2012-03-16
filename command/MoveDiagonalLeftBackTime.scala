package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalLeftBackTime(n : Int, l : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalLeftBackTime(n, l) 
	}
}