package command

import scaladrone.Drone
import scala.actors.Actor

case class turnRightUp(n : Int) extends Command {
	def execute(d : Drone){
		d.turnRightUp(n) 
	}
}