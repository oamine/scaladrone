package command

import scaladrone.Drone
import scala.actors.Actor

case class upByDistance(l : Float) extends Command {
	def execute(d : Drone){
		d.upByDistance(l) 
	}
}