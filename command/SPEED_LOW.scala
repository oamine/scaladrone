package command

import scaladrone.Drone
import scala.actors.Actor

case class SPEED_LOW extends Command {
	def execute(d : Drone){
		d.SPEED_LOW 
	}
}