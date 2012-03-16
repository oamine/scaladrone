package command

import scaladrone.Drone
import scala.actors.Actor

case class backByTime(n : Int) extends Command {
	def execute(d : Drone){
		d.backByTime(n) 
	}
}