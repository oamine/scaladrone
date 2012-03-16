package command

import scaladrone.Drone
import scala.actors.Actor

case class simple extends Command {
	def execute(d : Drone){
		d.simple 
	}
}