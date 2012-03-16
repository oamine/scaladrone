package command

import scaladrone.Drone
import scala.actors.Actor

case class isTrimSucceeded_$eq(o : Boolean) extends Command {
	def execute(d : Drone){
		d.isTrimSucceeded_$eq(o) 
	}
}