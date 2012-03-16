package command

import scaladrone.Drone
import scala.actors.Actor

case class getNbBoucle(l : Float) extends Command {
	def execute(d : Drone){
		d.getNbBoucle(l) 
	}
}