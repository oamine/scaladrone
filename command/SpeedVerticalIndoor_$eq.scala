package command

import scaladrone.Drone
import scala.actors.Actor

case class speedVerticalIndoor_$eq(n : Int) extends Command {
	def execute(d : Drone){
		d.speedVerticalIndoor_$eq(n) 
	}
}