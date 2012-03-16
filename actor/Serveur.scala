//
//import scala.actors.Actor
//import scala.actors.Actor._
//import scala.actors.remote.RemoteActor
//import scala.actors.remote.RemoteActor._
//import scala.actors.remote.Node
//
//
//
//
//class Serveur(val client : List[Actor]) extends Actor {
// 
//  def act () {
//    val fff = select(Node("127.0.0.1",9090),'Client) 
//    		
//	    for (i <- 0 to client.size-1){
//	    	println("j'envoie un message pour l'acteur "+i )
//	    	client(i) ! "message pour l'acteur "+i
//	    	Thread sleep 2000
//	    }
//	    
//	    loop {
//	      receive {
//	        case (name : String, actor : Actor) =>
//	          	println(a1)
//	          	actor ! "message que tu veux mettre"
//	          	Thread sleep 100
//	          	
//	      }
//	    }
//   
//    exit()
//  }
//  
//
// 
//}