package command

import scaladrone.Drone
import scala.actors.Actor

case class setAltitudeMax(l : Float) extends Command {
	def execute(d : Drone){
		d.setAltitudeMax(l) 
	}
}