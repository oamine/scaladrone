package scaladrone
import com.codeminders.ardrone.ARDrone
import com.codeminders.ardrone.ARDrone.LED
import com.codeminders.ardrone.ARDrone.Animation
import com.codeminders.ardrone.ARDrone.VideoChannel
import com.codeminders.ardrone.ARDrone.ConfigOption
import com.codeminders.ardrone.DroneVideoListener
import com.codeminders.ardrone.controllers
import com.codeminders.ardrone.NavDataListener
import org.apache.log4j.Logger
import java.io.IOException
import com.codeminders.ardrone.NavData
import com.codeminders.ardrone.DroneStatusChangeListener
import scala.collection.mutable.HashSet
import scala.collection.mutable.HashMap
import scala.dbc.result.Tuple

class ScalaDrone() extends ARDrone {
  

  def addImageListeners(v: DroneVideoListener) {
    this.addImageListener(v)
  }

  def clearImageListener() {
    this.clearImageListeners()
  }

  def removeImageListeners(v: DroneVideoListener) {
    this.removeImageListener(v)
  }

  def clearStatusChangeListener() {
    this.clearStatusChangeListeners()
  }

  def removeStatusChangeListeners(l: DroneStatusChangeListener) {
    this.removeStatusChangeListener(l)
  }

  def addStatusChangeListeners(l: DroneStatusChangeListener) {
    this.addStatusChangeListener(l)
  }

  def addNavDataListeners(na: NavDataListener) {
    this.addNavDataListener(na)
  }

  def removeNavDataListeners(nav: NavDataListener) {
    this.removeNavDataListener(nav)
  }

  def clearNavDataListener() {
    this.clearNavDataListeners()
  }

  def clearEmergencySignals() {
    this.clearEmergencySignal()
  }

  def disableAutomaticVideoBitrates() {
    this.disableAutomaticVideoBitrate()

  }

  def enableAutomatiqueVideoBitrate() {
    this.enableAutomaticVideoBitrate()
  }

  def isEmergencyModes(): Boolean = {
    return this.isEmergencyMode()
  }
  def isCombinedYaMode(): Boolean = {
    return this.isCombinedYawMode()
  }

  def navDataReceiveds(nd: NavData) {
    this.navDataReceived(nd);

  }
  def playAnimations(num_animation: Int, duration: Int) {
    this.playAnimation(num_animation, duration)
    Thread.sleep(3000)
  }

  def playAnimations(num_animation: Animation, duration: Int) {
    this.playAnimation(num_animation, duration)
    Thread.sleep(3000)
  }
  def playLed(num_animation_led: Int, frequence: Int, duration: Int) {
    this.playLED(num_animation_led, frequence, duration)
  }
  def playLed(num_animation_led: LED, frequence: Int, duration: Int) {
    this.playLED(num_animation_led, frequence, duration)
  }

  def selectVideoChannels(c: VideoChannel) {
    /*
         * Current implementation supports 4 different channels : -
         * ARDRONE_VIDEO_CHANNEL_HORI - ARDRONE_VIDEO_CHANNEL_VERT -
         * ARDRONE_VIDEO_CHANNEL_LARGE_HORI_SMALL_VERT -
         * ARDRONE_VIDEO_CHANNEL_LARGE_VERT_SMALL_HORI
         * 
         * AT command example : AT*CONFIG=605,"video:video_channel","2"
         */
    this.selectVideoChannel(c)
  }

  def sendDemoNavigationDatas() {
    this.sendDemoNavigationData()
  }

  def sendAllNavigationDatas() {

    this.sendAllNavigationData()
  }

  def sendEmergencySignals() {
    this.sendEmergencySignal();
  }

  def setConfigOptions(option: ConfigOption, value: String) {
    this.setConfigOption(option, value)

  }

  def setConfigOptions(name: String, value: String) {
    this.setConfigOption(name, value)
  }

  def setCombinedYawModes(combinedYawMode: Boolean) {
    this.setCombinedYawMode(combinedYawMode)
  }

  def connects() {
    this.connect()
  }

  def disconnects() {
    this.disconnect()
  }
  def hovers() {
    this.hover()
  }
  def lands() {
    this.land()
    Thread.sleep(3000)
  }
  def moves(left_rigth_tilt: Float, front_back_tilt: Float, vertical_speed: Float, angular_speed: Float) {
    this.move(left_rigth_tilt, front_back_tilt, vertical_speed, angular_speed)

  }

  def takeOffs() {
    this.takeOff()
  }

  def trims() {
    this.trim()
  }

  def videoFrameReceiveds(startX: Int, startY: Int, w: Int, h: Int, rbgArray: Array[Int], offset: Int, scansize: Int) {
    this.videoFrameReceived(startX, startY, w, h, rbgArray, offset, scansize);
  }

  def waitForReady() {

    this.waitForReady(3000)
  }

  // les commande ajouté
  def up() {
    this.moves(0, 0, 0.9921875f, 0);
  }

  def down() {
    this.moves(0, 0, -0.9921875f, 0);
  }

  def left() {
    this.moves(0, 0, 0, -0.9921875f);
  }

  def right() {
    this.moves(0, 0, 0, 0.9921875f);
  }

  def back() {
    this.moves(0, 0.9921875f, 0, 0);
  }

  def forward() {
    this.moves(0, -0.9921875f, 0, 0);
  }

  def moveLeft() {
    this.moves(-0.9921875f, 0, 0, 0);
  }

  def moveRight() {
    this.moves(0.9921875f, 0, 0, 0);
  }

  //tourne sur lui meme  gauche
  def turnLeft() {
    this.moves(0, -0.9921875f, 0, -0.9921875f)
  }

  def turnRight() {
    this.moves(0, -0.9921875f, 0, 0.9921875f)
  }

  // tourner en rond gauche 
  def turnAroundLeft() {
    this.moves(-0.9921875f, -0.9921875f, 0, -0.9921875f)
  }
  // tourner en rond droite
  def turnAroundRight() {
    this.moves(0.9921875f, -0.9921875f, 0, 0.9921875f);
  }

  def moveDiagonal(decalG_D: Float, Front_Back: Float, Up_Down: Float, turnG_D: Float) {
    this.moves(decalG_D, Front_Back, Up_Down, turnG_D)
  }

  def moveDiagonalLeftForward() {
    this.moves(-0.9921875f, -0.9921875f, 0, 0)
  }
  def moveDiagonalRightForward() {
    this.moves(0.9921875f, -0.9921875f, 0, 0)
  }

  def moveDiagonalLeftBack() {
    this.moves(0.9921875f, 0.10f, 0, 0)
  }
  def moveDiagonalRightBack() {
    this.moves(0.9921875f, 0.9921875f, 0, 0)
  }

  def moveDiagonalVertical(decalG_D: Float, Front_Back: Float, Up_Down: Float, turnG_D: Float) {
    this.moves(decalG_D, Front_Back, Up_Down, turnG_D)
  }
  // diago hauteur 
  def moveDiagonalUpLeft() {
    this.moves(-0.9921875f, 0, 0.9921875f, 0)
  }

  def moveDiagonalUpRight() {
    this.moves(0.9921875f, 0, 0.9921875f, 0)
  }

  def moveDiagonalDownLeft() {
    this.moves(-0.9921875f, 0, -0.9921875f, 0)
  }

  def moveDiagonalDownRight() {
    this.moves(0.9921875f, 0, -0.9921875f, 0)
  }

  def moveDiagonalBackUp() {
    this.moves(0, 0.9921875f, 0.9921875f, 0)
  }

  def moveDiagonalBackDown() {
    this.moves(0, 0.9921875f, -0.9921875f, 0)
  }

  def moveDiagonalForwardUp() {
    this.moves(0, -0.9921875f, 0.9921875f, 0)
  }

  def moveDiagonalForwardDown() {
    this.moves(0, -0.9921875f, -0.9921875f, 0)
  }

  def leAllTakeOff() {
    println("je me prepare")
    this.clearEmergencySignal()
    this.waitForReady()
    this.trims()
    println("Taking off")
    this.takeOff()
    Thread.sleep(2000)
  }

  def initialisation() {
    println("Initialisation ")
    this.clearEmergencySignal()
    this.waitForReady()
    this.trims()

  }
}

object ScalaDrone {
  def main(args: Array[String]) {
    val drone = new ScalaDrone
  }
  
}
