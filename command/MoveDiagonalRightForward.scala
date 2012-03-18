package command
/**
 * @author Amine O.
 * @author Sarah A.
 * 
 * Command for satellite drone
 */


import scaladrone.Drone
import scala.actors.Actor

case class MoveDiagonalRightForward(l : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalRightForward(l, f) 
	}
}