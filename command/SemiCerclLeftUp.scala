package command

import scaladrone.Drone
import scala.actors.Actor

case class semiCerclLeftUp(l : Float) extends Command {
	def execute(d : Drone){
		d.semiCerclLeftUp(l) 
	}
}