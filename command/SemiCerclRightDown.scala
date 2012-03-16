package command

import scaladrone.Drone
import scala.actors.Actor

case class semiCerclRightDown(l : Float) extends Command {
	def execute(d : Drone){
		d.semiCerclRightDown(l) 
	}
}