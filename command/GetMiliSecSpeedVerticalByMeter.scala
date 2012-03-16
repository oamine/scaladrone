package command

import scaladrone.Drone
import scala.actors.Actor

case class getMiliSecSpeedVerticalByMeter(l : Float, n : Int) extends Command {
	def execute(d : Drone){
		d.getMiliSecSpeedVerticalByMeter(l, n) 
	}
}