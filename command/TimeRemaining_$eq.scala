package command

import scaladrone.Drone
import scala.actors.Actor

case class timeRemaining_$eq(n : Int) extends Command {
	def execute(d : Drone){
		d.timeRemaining_$eq(n) 
	}
}