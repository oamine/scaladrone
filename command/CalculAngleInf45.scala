package command
/**
 * @author Amine O.
 * @author Sarah A.
 * 
 * Command for satellite drone
 * */
import scaladrone.Drone
import scala.actors.Actor

case class CalculAngleInf45(l : Float) extends Command {
	def execute(d : Drone){
		d.calculAngleInf45(l) 
	}
}