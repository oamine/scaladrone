package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalBackDown(n : Int, l : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalBackDown(n, l) 
	}
}