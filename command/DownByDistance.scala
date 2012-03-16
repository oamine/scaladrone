package command

import scaladrone.Drone
import scala.actors.Actor

case class downByDistance(l : Float) extends Command {
	def execute(d : Drone){
		d.downByDistance(l) 
	}
}