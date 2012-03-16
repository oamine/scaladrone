package command

import scaladrone.Drone
import scala.actors.Actor

case class setEnvironmentOutdoor(o : Boolean) extends Command {
	def execute(d : Drone){
		d.setEnvironmentOutdoor(o) 
	}
}