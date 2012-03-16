package command

import scaladrone.Drone
import scala.actors.Actor

case class isAuthorizedAltitude extends Command {
	def execute(d : Drone){
		d.isAuthorizedAltitude 
	}
}