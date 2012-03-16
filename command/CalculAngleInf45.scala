package command

import scaladrone.Drone
import scala.actors.Actor

case class calculAngleInf45(l : Float) extends Command {
	def execute(d : Drone){
		d.calculAngleInf45(l) 
	}
}