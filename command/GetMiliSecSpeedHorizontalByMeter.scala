package command

import scaladrone.Drone
import scala.actors.Actor

case class getMiliSecSpeedHorizontalByMeter(n : Int) extends Command {
	def execute(d : Drone){
		d.getMiliSecSpeedHorizontalByMeter(n) 
	}
}