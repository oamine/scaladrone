package command

import scaladrone.Drone
import scala.actors.Actor

case class setSpeedVerticalOutdoor(n : Int) extends Command {
	def execute(d : Drone){
		d.setSpeedVerticalOutdoor(n) 
	}
}