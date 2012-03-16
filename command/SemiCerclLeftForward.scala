package command

import scaladrone.Drone
import scala.actors.Actor

case class semiCerclLeftForward(l : Float) extends Command {
	def execute(d : Drone){
		d.semiCerclLeftForward(l) 
	}
}