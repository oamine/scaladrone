package command

import scaladrone.Drone
import scala.actors.Actor

case class takeOffWithAltitude(l : Float) extends Command {
	def execute(d : Drone){
		d.takeOffWithAltitude(l) 
	}
}