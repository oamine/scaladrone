package command

import scaladrone.Drone
import scala.actors.Actor

case class semiCerclBackRight(l : Float) extends Command {
	def execute(d : Drone){
		d.semiCerclBackRight(l) 
	}
}