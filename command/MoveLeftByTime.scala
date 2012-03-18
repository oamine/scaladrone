package command
/**
 * @author Amine O.
 * @author Sarah A.
 * 
 * Command for satellite drone
 */


import scaladrone.Drone
import scala.actors.Actor

case class MoveLeftByTime(n : Int) extends Command {
	def execute(d : Drone){
		d.moveLeftByTime(n) 
	}
}