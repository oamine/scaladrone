package command

import scaladrone.Drone
import scala.actors.Actor

case class isBatteryHigh extends Command {
	def execute(d : Drone){
		d.isBatteryHigh 
	}
}