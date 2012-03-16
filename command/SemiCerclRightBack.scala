package command

import scaladrone.Drone
import scala.actors.Actor

case class semiCerclRightBack(l : Float) extends Command {
	def execute(d : Drone){
		d.semiCerclRightBack(l) 
	}
}