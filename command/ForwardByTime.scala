package command

import scaladrone.Drone
import scala.actors.Actor

case class forwardByTime(n : Int) extends Command {
	def execute(d : Drone){
		d.forwardByTime(n) 
	}
}