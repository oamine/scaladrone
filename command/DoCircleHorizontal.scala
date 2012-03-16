package command

import scaladrone.Drone
import scala.actors.Actor

case class doCircleHorizontal(l : Float) extends Command {
	def execute(d : Drone){
		d.doCircleHorizontal(l) 
	}
}