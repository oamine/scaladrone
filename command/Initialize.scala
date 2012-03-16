package command

import scaladrone.Drone
import scala.actors.Actor

case class initialize extends Command {
	def execute(d : Drone){
		d.initialize 
	}
}