package command

import scaladrone.Drone
import scala.actors.Actor

case class turnRightByDegre(n : Int) extends Command {
	def execute(d : Drone){
		d.turnRightByDegre(n) 
	}
}