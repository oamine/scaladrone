package command

import scaladrone.Drone
import scala.actors.Actor

case class getRotationTimeByAngle(n : Int) extends Command {
	def execute(d : Drone){
		d.getRotationTimeByAngle(n) 
	}
}