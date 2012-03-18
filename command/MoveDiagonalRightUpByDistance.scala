package command
/**
 * @author Amine O.
 * @author Sarah A.
 * 
 * Command for satellite drone
 */


import scaladrone.Drone
import scala.actors.Actor

case class MoveDiagonalRightUpByDistance(l : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalRightUpByDistance(l, f) 
	}
}