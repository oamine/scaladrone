package command

import scaladrone.Drone
import scala.actors.Actor

case class semiCerclRightForward(l : Float) extends Command {
	def execute(d : Drone){
		d.semiCerclRightForward(l) 
	}
}