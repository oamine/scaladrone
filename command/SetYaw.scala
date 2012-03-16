package command

import scaladrone.Drone
import scala.actors.Actor

case class setYaw(l : Float) extends Command {
	def execute(d : Drone){
		d.setYaw(l) 
	}
}