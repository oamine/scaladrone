package command

import scaladrone.Drone
import scala.actors.Actor

case class setSpeedHorizontal(n : Int) extends Command {
	def execute(d : Drone){
		d.setSpeedHorizontal(n) 
	}
}