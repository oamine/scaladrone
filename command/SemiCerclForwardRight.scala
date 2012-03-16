package command

import scaladrone.Drone
import scala.actors.Actor

case class semiCerclForwardRight(l : Float) extends Command {
	def execute(d : Drone){
		d.semiCerclForwardRight(l) 
	}
}