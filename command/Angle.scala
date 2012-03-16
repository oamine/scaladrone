package command

import scaladrone.Drone
import scala.actors.Actor

case class angle extends Command {
	def execute(d : Drone){
		d.angle 
	}
}