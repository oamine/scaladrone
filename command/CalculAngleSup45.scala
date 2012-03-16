package command

import scaladrone.Drone
import scala.actors.Actor

case class calculAngleSup45(l : Float) extends Command {
	def execute(d : Drone){
		d.calculAngleSup45(l) 
	}
}