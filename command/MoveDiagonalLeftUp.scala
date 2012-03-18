package command
/**
 * @author Amine O.
 * @author Sarah A.
 * 
 * Command for satellite drone
 */


import scaladrone.Drone
import scala.actors.Actor

case class MoveDiagonalLeftUp(n : Int, l : Float) extends Command {
	def execute(d : Drone){
		d.moveDiagonalLeftUp(n, l) 
	}
}