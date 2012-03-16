package command

import scaladrone.Drone
import scala.actors.Actor

case class upByTime(n : Int) extends Command {
	def execute(d : Drone){
		d.upByTime(n) 
	}
}