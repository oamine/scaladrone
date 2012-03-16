package command

import scaladrone.Drone
import scala.actors.Actor

case class isEmergency_$eq(o : Boolean) extends Command {
	def execute(d : Drone){
		d.isEmergency_$eq(o) 
	}
}