package command

import scaladrone.Drone
import scala.actors.Actor

case class getClass extends Command {
	def execute(d : Drone){
		d.getClass 
	}
}