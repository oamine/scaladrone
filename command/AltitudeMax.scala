package command

import scaladrone.Drone
import scala.actors.Actor

case class altitudeMax extends Command {
	def execute(d : Drone){
		d.altitudeMax 
	}
}