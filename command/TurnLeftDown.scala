package command

import scaladrone.Drone
import scala.actors.Actor

case class turnLeftDown(n : Int) extends Command {
	def execute(d : Drone){
		d.turnLeftDown(n) 
	}
}