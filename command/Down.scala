package command

import scaladrone.Drone
import scala.actors.Actor

case class down(l : Float) extends Command {
	def execute(d : Drone){
		d.down(l) 
	}
}