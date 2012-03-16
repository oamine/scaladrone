package command

import scaladrone.Drone
import scala.actors.Actor

case class radToDegre(l : Float) extends Command {
	def execute(d : Drone){
		d.radToDegre(l) 
	}
}