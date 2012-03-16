package command

import scaladrone.Drone
import scala.actors.Actor

case class semiCerclLeftDown(l : Float) extends Command {
	def execute(d : Drone){
		d.semiCerclLeftDown(l) 
	}
}