package command

import scaladrone.Drone
import scala.actors.Actor

case class semiCerclRightUp(l : Float) extends Command {
	def execute(d : Drone){
		d.semiCerclRightUp(l) 
	}
}