package command

import scaladrone.Drone
import scala.actors.Actor

case class downByTime(n : Int) extends Command {
	def execute(d : Drone){
		d.downByTime(n) 
	}
}