package command

import scaladrone.Drone
import scala.actors.Actor

case class isBatteryHigh_$eq(o : Boolean) extends Command {
	def execute(d : Drone){
		d.isBatteryHigh_$eq(o) 
	}
}