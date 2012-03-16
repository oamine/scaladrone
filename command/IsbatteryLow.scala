package command

import scaladrone.Drone
import scala.actors.Actor

case class isbatteryLow extends Command {
	def execute(d : Drone){
		d.isbatteryLow 
	}
}