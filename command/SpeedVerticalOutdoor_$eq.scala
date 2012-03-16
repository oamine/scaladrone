package command

import scaladrone.Drone
import scala.actors.Actor

case class speedVerticalOutdoor_$eq(n : Int) extends Command {
	def execute(d : Drone){
		d.speedVerticalOutdoor_$eq(n) 
	}
}