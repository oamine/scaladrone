package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalBackUp(n : Int, l : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalBackUp(n, l) 
	}
}