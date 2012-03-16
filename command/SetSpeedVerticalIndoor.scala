package command

import scaladrone.Drone
import scala.actors.Actor

case class setSpeedVerticalIndoor(n : Int) extends Command {
	def execute(d : Drone){
		d.setSpeedVerticalIndoor(n) 
	}
}