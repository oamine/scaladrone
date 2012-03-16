package command

import scaladrone.Drone
import scala.actors.Actor

case class moveDiagonalBackDownByDistance(l : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalBackDownByDistance(l, f) 
	}
}