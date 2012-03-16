package command

import scaladrone.Drone
import scala.actors.Actor

case class isdowning_$eq(o : Boolean) extends Command {
	def execute(d : Drone){
		d.isdowning_$eq(o) 
	}
}