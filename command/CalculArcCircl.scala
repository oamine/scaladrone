package command

import scaladrone.Drone
import scala.actors.Actor

case class calculArcCircl(l : Float) extends Command {
	def execute(d : Drone){
		d.calculArcCircl(l) 
	}
}