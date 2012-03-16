package command

import scaladrone.Drone
import scala.actors.Actor

case class setAltitudeIfBatteryLow extends Command {
	def execute(d : Drone){
		d.setAltitudeIfBatteryLow 
	}
}