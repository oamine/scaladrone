package command

/**
 * @author Amine O.
 * @author Sarah A.
 * 
 * Command for satellite drone
 */


import scaladrone.Drone
import scala.actors.Actor

case class ALTIUDE_MAX extends Command {
	def execute(d : Drone){
		d.ALTIUDE_MAX 
	}
}