package command

import scaladrone.Drone
import scala.actors.Actor

case class turnRightDown(n : Int) extends Command {
	def execute(d : Drone){
		d.turnRightDown(n) 
	}
}