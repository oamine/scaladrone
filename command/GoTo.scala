package command

import scaladrone.Drone
import scala.actors.Actor

case class goTo(l : Float) extends Command {
	def execute(d : Drone){
		d.goTo(l) 
	}
}