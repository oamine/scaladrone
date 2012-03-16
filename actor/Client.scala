import scala.actors.Actor
import scala.actors.Actor._
import scala.actors.remote.RemoteActor
import scala.actors.remote.RemoteActor._
import scala.actors.remote.Node



class Client() extends Actor {
 
  def act () {
//    val client = select(Node("127.0.0.1",9090),'Serveur)
//    client ! "ok"
    this.
    loop {
      react {
        case s1 =>
        sender ! "message gentil"
          println("Ceci est le message que j'ai reçu : "+s1)
          Thread sleep 100
          
          
      }
    }
    
    println("ok") 
    exit()
  }
  
  def coucou(){
    println("coucou")
  }

 
}