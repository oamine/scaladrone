package command

import scaladrone.Drone
import scala.actors.Actor

case class moveRightByDistance(l : Float) extends Command {
	def execute(d : Drone){
		d.moveRightByDistance(l) 
	}
}