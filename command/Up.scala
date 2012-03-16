package command

import scaladrone.Drone
import scala.actors.Actor

case class up(l : Float) extends Command {
	def execute(d : Drone){
		d.up(l) 
	}
}