package command

import scaladrone.Drone
import scala.actors.Actor

case class getListSpeed extends Command {
	def execute(d : Drone){
		d.getListSpeed 
	}
}