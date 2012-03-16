package command

import scaladrone.Drone
import scala.actors.Actor

case class degreeToRad(n : Int) extends Command {
	def execute(d : Drone){
		d.degreeToRad(n) 
	}
}