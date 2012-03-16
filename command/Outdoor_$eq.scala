package command

import scaladrone.Drone
import scala.actors.Actor

case class outdoor_$eq(o : Boolean) extends Command {
	def execute(d : Drone){
		d.outdoor_$eq(o) 
	}
}