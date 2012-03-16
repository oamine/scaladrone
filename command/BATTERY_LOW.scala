package command

import scaladrone.Drone
import scala.actors.Actor

case class BATTERY_LOW extends Command {
	def execute(d : Drone){
		d.BATTERY_LOW 
	}
}