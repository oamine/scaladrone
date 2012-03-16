package command

import scaladrone.Drone
import scala.actors.Actor

case class moveLeftByTime(n : Int) extends Command {
	def execute(d : Drone){
		d.moveLeftByTime(n) 
	}
}