package command

import scaladrone.Drone
import scala.actors.Actor

case class getSpeedHorizontal extends Command {
	def execute(d : Drone){
		d.getSpeedHorizontal 
	}
}