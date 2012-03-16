package command

import scaladrone.Drone
import scala.actors.Actor

case class getAltitudeMin extends Command {
	def execute(d : Drone){
		d.getAltitudeMin 
	}
}