package command

import scaladrone.Drone
import scala.actors.Actor

case class turnLeftByDegre(n : Int) extends Command {
	def execute(d : Drone){
		d.turnLeftByDegre(n) 
	}
}