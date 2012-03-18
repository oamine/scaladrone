package command
/**
 * @author Amine O.
 * @author Sarah A.
 * 
 * Command for satellite drone
 */


import scaladrone.Drone
import scala.actors.Actor

case class MoveLeftByDistance(l : Float) extends Command {
	def execute(d : Drone){
		d.moveLeftByDistance(l) 
	}
}