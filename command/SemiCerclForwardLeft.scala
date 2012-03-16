package command

import scaladrone.Drone
import scala.actors.Actor

case class semiCerclForwardLeft(l : Float) extends Command {
	def execute(d : Drone){
		d.semiCerclForwardLeft(l) 
	}
}