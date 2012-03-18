package command
/**
 * @author Amine O.
 * @author Sarah A.
 * 
 * Command for satellite drone
 */


import scaladrone.Drone
import scala.actors.Actor

case class IsAuthorizedValue(l : Float, fl : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.isAuthorizedValue(l, fl, f) 
	}
}