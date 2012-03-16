package command

import scaladrone.Drone
import scala.actors.Actor

case class animationSimple extends Command {
	def execute(d : Drone){
		d.animationSimple 
	}
}