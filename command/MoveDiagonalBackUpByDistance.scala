package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalBackUpByDistance(l : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalBackUpByDistance(l, f) 
	}
}