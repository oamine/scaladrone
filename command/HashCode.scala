package command

import scaladrone.Drone
import scala.actors.Actor

case class hashCode extends Command {
	def execute(d : Drone){
		d.hashCode 
	}
}