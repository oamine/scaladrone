package command

import scaladrone.Drone
import scala.actors.Actor

case class squareHorizontal(n : Int) extends Command {
	def execute(d : Drone){
		d.squareHorizontal(n) 
	}
}