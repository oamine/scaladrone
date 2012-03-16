package command

import scaladrone.Drone
import scala.actors.Actor

case class altitude extends Command {
	def execute(d : Drone){
		d.altitude 
	}
}