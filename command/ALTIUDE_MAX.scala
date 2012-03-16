package command

import scaladrone.Drone
import scala.actors.Actor

case class ALTIUDE_MAX extends Command {
	def execute(d : Drone){
		d.ALTIUDE_MAX 
	}
}