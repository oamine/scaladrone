package actor
import scala.actors.Actor
import scala.actors.Actor._
import scala.actors.remote.RemoteActor
import scala.actors.remote.RemoteActor._
import scala.actors.remote.Node
import command._
import scaladrone.Drone




class Serveur(val client : List[SatelliteActor]) extends Drone with Actor {
  def sendCommand(cmd : Command){
    	    for (i <- 0 to client.size-1){
	    	println("I send an instruction for "+client(i).getName )
	    	client(i) ! cmd 
	    }
  }
  
  def act () {
    this connect()
    sendCommand(new Connect)
    this takeOffWithAltitude(100)
    sendCommand(new TakeOffWithAltitude(100))
    this trim()
    sendCommand(new Trim)
    Thread sleep 2000
    this land()
    sendCommand(new Land)

	    
	    loop {
	      react {
	        /**
	         * Just for receive message
	         * May be we can do a log system
	         * or a check system for verify if
	         * the satellite drone received all
	         * message and resend it if not
	         * 
	         */
	        case x => println(x)
	          	
	      }
	    }
   
    exit()
  }
  

 
}