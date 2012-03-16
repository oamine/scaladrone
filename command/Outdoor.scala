package command

import scaladrone.Drone
import scala.actors.Actor

case class outdoor extends Command {
	def execute(d : Drone){
		d.outdoor 
	}
}