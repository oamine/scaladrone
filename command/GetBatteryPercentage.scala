package command

import scaladrone.Drone
import scala.actors.Actor

case class getBatteryPercentage extends Command {
	def execute(d : Drone){
		d.getBatteryPercentage 
	}
}