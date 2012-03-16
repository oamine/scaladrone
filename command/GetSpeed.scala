package command

import scaladrone.Drone
import scala.actors.Actor

case class getSpeed extends Command {
	def execute(d : Drone){
		d.getSpeed 
	}
}