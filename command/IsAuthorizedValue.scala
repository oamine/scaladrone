package command

import scaladrone.Drone
import scala.actors.Actor

case class isAuthorizedValue(l : Float, fl : Float, f : Float) extends Command {
	def execute(d : Drone){
		d.isAuthorizedValue(l, fl, f) 
	}
}