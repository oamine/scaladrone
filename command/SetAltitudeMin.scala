package command

import scaladrone.Drone
import scala.actors.Actor

case class setAltitudeMin(l : Float) extends Command {
	def execute(d : Drone){
		d.setAltitudeMin(l) 
	}
}