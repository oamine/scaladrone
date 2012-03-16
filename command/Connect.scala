package command

import scaladrone.Drone
import scala.actors.Actor

case class connect extends Command {
	def execute(d : Drone){
		d.connect 
	}
}