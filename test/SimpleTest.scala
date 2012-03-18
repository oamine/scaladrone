package test
import scaladrone.Drone

object SimpleTest {
  def main(args: Array[String]) {
    var mydrone = new Drone()
    mydrone initialize()
    mydrone ready()
    mydrone takeOff()
    mydrone trim()
    mydrone up(50)
    Thread sleep 2000
    mydrone land()     
  }
}