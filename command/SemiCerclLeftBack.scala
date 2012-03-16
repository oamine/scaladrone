package command

import scaladrone.Drone
import scala.actors.Actor

case class semiCerclLeftBack(l : Float) extends Command {
	def execute(d : Drone){
		d.semiCerclLeftBack(l) 
	}
}