package command

import scaladrone.Drone
import scala.actors.Actor

case class isdowning extends Command {
	def execute(d : Drone){
		d.isdowning 
	}
}