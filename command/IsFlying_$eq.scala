package command
/**
 * @author Amine O.
 * @author Sarah A.
 * 
 * Command for satellite drone
 */


import scaladrone.Drone
import scala.actors.Actor

case class IsFlying_$eq(o : Boolean) extends Command {
	def execute(d : Drone){
		d.isFlying_$eq(o) 
	}
}