package command

import scaladrone.Drone
import scala.actors.Actor

case class getSpeedVertical extends Command {
	def execute(d : Drone){
		d.getSpeedVertical 
	}
}