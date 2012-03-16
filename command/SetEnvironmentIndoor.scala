package command

import scaladrone.Drone
import scala.actors.Actor

case class setEnvironmentIndoor(o : Boolean) extends Command {
	def execute(d : Drone){
		d.setEnvironmentIndoor(o) 
	}
}