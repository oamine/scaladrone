package command

import scaladrone.Drone
import scala.actors.Actor

case class getDrone extends Command {
	def execute(d : Drone){
		d.getDrone 
	}
}