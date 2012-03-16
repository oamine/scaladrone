package command

import scaladrone.Drone
import scala.actors.Actor

case class squareVertical(n : Int) extends Command {
	def execute(d : Drone){
		d.squareVertical(n) 
	}
}