package command

import scaladrone.Drone
import scala.actors.Actor

case class moveLeftByDistance(l : Float) extends Command {
	def execute(d : Drone){
		d.moveLeftByDistance(l) 
	}
}