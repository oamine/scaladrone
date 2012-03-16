package command

import scaladrone.Drone
import scala.actors.Actor

case class doMovementButterflyLeftRight(n : Int) extends Command {
	def execute(d : Drone){
		d.doMovementButterflyLeftRight(n) 
	}
}