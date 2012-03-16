package command

import scaladrone.Drone
import scala.actors.Actor

case class battery extends Command {
	def execute(d : Drone){
		d.battery 
	}
}