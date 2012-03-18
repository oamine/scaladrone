package test
import actor.SatelliteActor
import actor.Serveur


/**
 * Main test for actor
 * */
object TestActor {

  def main(args: Array[String]) {

    
    val a1 = new SatelliteActor(12345,"drone 1")
    val a2 = new SatelliteActor(12346,"drone 2")
    val a3 = new SatelliteActor(12347,"drone 3")
    val list =  List(a1,a2,a3)
    val s1 = new Serveur(list)

    
    a1.start
    a2.start
    a3.start  
    s1.start

    Thread sleep 1000

  }
}