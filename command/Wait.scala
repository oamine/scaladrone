package command

import scaladrone.Drone
import scala.actors.Actor

case class wait(o : Long, n : Int) extends Command {
	def execute(d : Drone){
		d.wait(o, n) 
	}
}