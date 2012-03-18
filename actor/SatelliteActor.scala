package actor
import scala.actors._
import scala.actors.Actor
import scala.actors.Actor._
import scala.actors.remote.RemoteActor
import scala.actors.remote.Node
import scala.actors.remote.RemoteActor._
import scaladrone.Drone
import command._


case object Up
case object Front


class SatelliteActor(port : Int,name : String) extends Drone with Actor  {
  /**
   *  Variable for the Serveur Actor
   * */
  def getPort = port
  def getName = name
  
  RemoteActor.classLoader = getClass().getClassLoader()
  RemoteActor.alive(port)
  RemoteActor.register('myName, this)
  
  
  def affiche(j : Int){    
    for (i <- 1 to j)
    	println(i)
  }
  /**
   * convert message from server Actor
   * * @return Array Of String 
   * */
  def convert(s: String) : Array[String] = {
		  return s split("\\|")    
  }
  
  def toto : String = {
    return "salut"
  }
  

  def act(){
    loop{
      react { 

        
        case x : String => println(name + " => "+x)
        case x : up  => x.execute(this)
        case x : Down => x.execute(this)     
      //  case x : TurnLeft => println(x)
        case _ => println(name+" -> rien")
      }
      
    }
    
  }

}