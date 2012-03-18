package command

import scaladrone.Drone
import scala.actors.Actor

case class CalculArcCircl(l : Float) extends Command {
	def execute(d : Drone){
		d.calculArcCircl(l) 
	}
}