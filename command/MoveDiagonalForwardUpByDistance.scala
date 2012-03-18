package command
/**
 * @author Amine O.
 * @author Sarah A.
 * 
 * Command for satellite drone
 */


import scaladrone.Drone
import scala.actors.Actor

case class MoveDiagonalForwardUpByDistance(l : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalForwardUpByDistance(l, f) 
	}
}