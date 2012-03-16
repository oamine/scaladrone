package command

import scaladrone.Drone
import scala.actors.Actor

case class getAltitudeMax extends Command {
	def execute(d : Drone){
		d.getAltitudeMax 
	}
}