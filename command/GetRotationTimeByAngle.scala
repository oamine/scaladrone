package command
/**
 * @author Amine O.
 * @author Sarah A.
 * 
 * Command for satellite drone
 */


import scaladrone.Drone
import scala.actors.Actor

case class GetRotationTimeByAngle(n : Int) extends Command {
	def execute(d : Drone){
		d.getRotationTimeByAngle(n) 
	}
}