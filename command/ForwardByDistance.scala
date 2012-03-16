package command

import scaladrone.Drone
import scala.actors.Actor

case class forwardByDistance(l : Float) extends Command {
	def execute(d : Drone){
		d.forwardByDistance(l) 
	}
}