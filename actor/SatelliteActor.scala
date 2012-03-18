package actor

/**
 * @author Amine O.
 * @author Sarah A.
 * 
 * Satellite actor.
 * This class represent a slave drone.
 * the command come from the master drone
 */

import scala.actors._
import scala.actors.Actor
import scala.actors.Actor._
import scala.actors.remote.RemoteActor
import scala.actors.remote.Node
import scala.actors.remote.RemoteActor._
import scaladrone.Drone
import command._


class SatelliteActor(port : Int,name : String) extends Drone with Actor  {

  def getPort = port
  def getName = name
  
  RemoteActor.classLoader = getClass().getClassLoader()
  RemoteActor.alive(port)
  RemoteActor.register('myName, this)
  
    

  def act(){
    loop{
      react {      
        case x : AnimationSimple  => x.execute(this)
        case x : Attering => x.execute(this)
        case x : BackByDistance => x.execute(this)
        case x : BackByTime => x.execute(this)
        case x : CalculAngleInf45 => x.execute(this)
        case x : CalculAngleSup45 => x.execute(this)
        case x : CalculArcCircl => x.execute(this)
        case x : Connect => x.execute(this)
        case x : Disconnect => x.execute(this)
        case x : DoCircleHorizontal => x.execute(this)
        case x : DoCircleVertical => x.execute(this)
        case x : DoForwardTurnAroundForward => x.execute(this)
        case x : DoMovementButterFlyDiagonalForwardLeft => x.execute(this)
        case x : DoMovementButterFlyDiagonalForwardRight => x.execute(this)
        case x : DoMovementButterflyForwardBack => x.execute(this)
        case x : DoMovementButterflyLeftRight => x.execute(this)
        case x : DoMovementEight => x.execute(this)
        case x : Down => x.execute(this)
        case x : DownByDistance => x.execute(this)
        case x : DownByTime => x.execute(this)
        case x : ForwardByDistance => x.execute(this)
        case x : ForwardByTime => x.execute(this)
        case x : GoTo => x.execute(this)
        case x : Initialize => x.execute(this)
        case x : MoveDiagonalBackDown => x.execute(this)
        case x : MoveDiagonalBackDownByDistance => x.execute(this)
        case x : MoveDiagonalBackUp => x.execute(this)
        case x : MoveDiagonalBackUpByDistance => x.execute(this)
        case x : MoveDiagonalForwardDown => x.execute(this)
        case x : MoveDiagonalForwardDownByDistance => x.execute(this)
        case x : MoveDiagonalForwardUp => x.execute(this)
        case x : MoveDiagonalForwardUpByDistance => x.execute(this)
        case x : MoveDiagonalLeftBack => x.execute(this)
        case x : MoveDiagonalLeftBackTime => x.execute(this)
        case x : MoveDiagonalLeftDown => x.execute(this)
        case x : MoveDiagonalLeftDownByDistance => x.execute(this)
        case x : MoveDiagonalLeftForward => x.execute(this)
        case x : MoveDiagonalLeftForwardTime => x.execute(this)
        case x : MoveDiagonalLeftUp => x.execute(this)
        case x : MoveDiagonalLeftUpByDistance => x.execute(this)
        case x : MoveDiagonalRightBack => x.execute(this)
        case x : MoveDiagonalRightBackTime => x.execute(this)
        case x : MoveDiagonalRightDown => x.execute(this)
        case x : MoveDiagonalRightDownByDistance => x.execute(this)
        case x : MoveDiagonalRightForward => x.execute(this)
        case x : MoveDiagonalRightForwardTime => x.execute(this)
        case x : MoveDiagonalRightUp => x.execute(this)
        case x : MoveDiagonalRightUpByDistance => x.execute(this)
        case x : MoveLeftByDistance => x.execute(this)
        case x : MoveLeftByTime => x.execute(this)
        case x : MoveRightByDistance => x.execute(this)
        case x : MoveRightByTime => x.execute(this)
        case x : Notify => x.execute(this)
        case x : NotifyAll => x.execute(this)
        case x : Ready => x.execute(this)
        case x : SemiCerclBackLeft => x.execute(this)
        case x : SemiCerclBackRight => x.execute(this)
        case x : SemiCerclForwardLeft => x.execute(this)
        case x : SemiCerclForwardRight => x.execute(this)
        case x : SemiCerclLeftBack => x.execute(this)
        case x : SemiCerclLeftDown => x.execute(this)
        case x : SemiCerclLeftForward => x.execute(this)
        case x : SemiCerclLeftUp => x.execute(this)
        case x : SemiCerclRightBack => x.execute(this)
        case x : SemiCerclRightDown => x.execute(this)
        case x : SemiCerclRightForward => x.execute(this)
        case x : SemiCerclRightUp => x.execute(this)
        case x : SetAltitudeIfBatteryLow => x.execute(this)
        case x : SetAltitudeMax => x.execute(this)
        case x : SetAltitudeMin => x.execute(this)
        case x : SetEnvironmentIndoor => x.execute(this)
        case x : SetEnvironmentOutdoor=> x.execute(this)
        case x : SetSpeedHorizontal=> x.execute(this)
        case x : SetSpeedVerticalIndoor=> x.execute(this)
        case x : SetSpeedVerticalOutdoor=> x.execute(this)
        case x : SetYaw=> x.execute(this)
        case x : Simple=> x.execute(this)
        case x : SpeedByTime=> x.execute(this)
        case x : SpeedListInDoor=> x.execute(this)
        case x : SpeedListOutDoor=> x.execute(this)
        case x : SpeedVerticalIndoor=> x.execute(this)        
        case x : SpeedVerticalOutdoor_$eq=> x.execute(this)
        case x : SpeedVerticalOutdoor=> x.execute(this)
        case x : SquareHorizontal=> x.execute(this)
        case x : TakeOff=> x.execute(this)
        case x : SquareVertical => x.execute(this)
        case x : TakeOffWithAltitude=> x.execute(this)
        case x : Trim=> x.execute(this)
        case x : TurnLeftByDegre=> x.execute(this)        
        case x : TurnLeftDown=> x.execute(this)        
        case x : TurnLeftUp=> x.execute(this)        
        case x : TurnRightByDegre=> x.execute(this)        
        case x : TurnRightDown=> x.execute(this)        
        case x : TurnRightUp=> x.execute(this)        
        case x : Up=> x.execute(this)        
        case x : UpByDistance=> x.execute(this)        
        case x : UpByTime=> x.execute(this)        
        case x : Wait=> x.execute(this)           
        case _ => println(name+" -> command not recognized")
      }
      
    }
    
  }

}