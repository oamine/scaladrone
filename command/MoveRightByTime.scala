package command

import scaladrone.Drone
import scala.actors.Actor

case class moveRightByTime(n : Int) extends Command {
	def execute(d : Drone){
		d.moveRightByTime(n) 
	}
}