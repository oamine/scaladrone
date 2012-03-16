package command

import scaladrone.Drone
import scala.actors.Actor

case class doCircleVertical(l : Float) extends Command {
	def execute(d : Drone){
		d.doCircleVertical(l) 
	}
}