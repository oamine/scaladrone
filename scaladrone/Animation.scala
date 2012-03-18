package scaladrone

class Annimation {
  
  val drone = new Drone
  
  def doMovementEight(diametre:Float){
    drone.semiCerclForwardRight(diametre)
    drone.semiCerclBackLeft(diametre)
    drone.semiCerclBackRight(diametre)
    drone.semiCerclForwardLeft(diametre)
  }
  
  def doMovementButterfly(){
  for(i<- 0 to 4){
    drone.moveLeftByTime(10)
    drone.moveRightByTime(10)
  } 
  }

}