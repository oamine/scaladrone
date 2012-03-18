package command

/**
 * @author Amine O.
 * @author Sarah A.
 * 
 * Command for satellite drone
 */


import scaladrone.Drone
import scala.actors.Actor

case class TurnRightDown(n : Int) extends Command {
	def execute(d : Drone){
		d.turnRightDown(n) 
	}
}