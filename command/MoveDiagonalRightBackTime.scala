package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalRightBackTime(n : Int, l : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalRightBackTime(n, l) 
	}
}