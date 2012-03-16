package command

import scaladrone.Drone
import scala.actors.Actor

case class doForwardTurnAroundForward(l : Float) extends Command {
	def execute(d : Drone){
		d.doForwardTurnAroundForward(l) 
	}
}