package command

import scaladrone.Drone
import scala.actors.Actor

case class altitudeMin extends Command {
	def execute(d : Drone){
		d.altitudeMin 
	}
}