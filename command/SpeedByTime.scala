package command

import scaladrone.Drone
import scala.actors.Actor

case class speedByTime extends Command {
	def execute(d : Drone){
		d.speedByTime 
	}
}