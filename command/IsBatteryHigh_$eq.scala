package command
/**
 * @author Amine O.
 * @author Sarah A.
 * 
 * Command for satellite drone
 */


import scaladrone.Drone
import scala.actors.Actor

case class IsBatteryHigh_$eq(o : Boolean) extends Command {
	def execute(d : Drone){
		d.isBatteryHigh_$eq(o) 
	}
}