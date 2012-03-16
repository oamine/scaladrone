package command

import scaladrone.Drone
import scala.actors.Actor

case class altitude_$eq(l : Float) extends Command {
	def execute(d : Drone){
		d.altitude_$eq(l) 
	}
}