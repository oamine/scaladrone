package command

import scaladrone.Drone
import scala.actors.Actor

case class battery_$eq(n : Int) extends Command {
	def execute(d : Drone){
		d.battery_$eq(n) 
	}
}