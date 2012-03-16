package command

import scaladrone.Drone
import scala.actors.Actor

case class semiCerclBackLeft(l : Float) extends Command {
	def execute(d : Drone){
		d.semiCerclBackLeft(l) 
	}
}