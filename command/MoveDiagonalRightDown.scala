package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalRightDown(n : Int, l : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalRightDown(n, l) 
	}
}