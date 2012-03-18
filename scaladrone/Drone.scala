package scaladrone
import com.codeminders.ardrone.ARDrone
import scala.collection.mutable.HashMap
import com.codeminders.ardrone.ARDrone.LED
import com.codeminders.ardrone.ARDrone.ConfigOption
import com.codeminders.ardrone.NavData
import com.codeminders.ardrone.NavDataListener
import org.apache.log4j.Logger
import com.codeminders.ardrone.DroneStatusChangeListener
import com.codeminders.ardrone.NavData.FlyingState
import com.codeminders.ardrone.NavData.Mode
import com.codeminders.ardrone.NavData.CtrlState
import java.util.prefs.Preferences
import scala.actors.Actor
import scala.actors.Actor
import scala.actors.Actor._
import scala.actors.remote.RemoteActor
import scala.actors.remote.RemoteActor._
import scala.actors.remote.Node
import java.util.Timer
import java.util.Date
import tools._
import java.util.StringTokenizer
import tools.MyTimer
import scala.collection.mutable._
import java.io.FileWriter
import java.io.IOException
import java.io.BufferedWriter
import java.io.PrintWriter

/**
 * this class contain a drone and a navdata
 * @author Amine Ouelhadj
 * @author Sarah Aichaoui
 * @Version 0.1
 * @constructor create a new drone and navdata
 */

class Drone() extends NavDataListener with DroneStatusChangeListener {
  val BATTERY_LOW = 12
  val VAL_SLEEP = 200
  val SPEED_VERTICAL_DEFAULT = 200
  val SPEED_VERTICAL_SPEED_MIN = 200
  val SPEED_VERTICAL_SPEED_MAX = 2000
  val ALTITUDE_MIN_CURRENT = 50f
  val ALTITUDE_MAX_CURRENT = 1000f
  val ALTIUDE_MAX = 10000f
  val ALTITUDE_MAX_IF_LOW_BATTERY_TOLERANCE = 20
  val ALTITUDE_MAX_IF_LOW_BATTERY = 80
  val ALTITUDE_MAX_IF_VERY_LOW_BATTERY = 55
  val SPEED_LOW = 1
  val SPEED_MEDIUM = 2
  val SPEED_FAST = 3
  var isdowning = false

  /**
   * Speed indoor by metter
   * id,angle, nb loop
   */
  val speedListInDoor = List((SPEED_LOW, "0.1", 4),
    (SPEED_MEDIUM, "0.2", 2),
    (SPEED_FAST, "0.3", 1))

  /**
   * Speed outdoor by metter
   * id,angle, nb loop
   *  TODO
   */
  val speedListOutDoor = List((SPEED_LOW, "0.1", 4),
    (SPEED_MEDIUM, "0.2", 2),
    (SPEED_FAST, "0.3", 1))

  // pour 1 metre combien de temps met
  val speedByTime = List((SPEED_LOW, 1000), (SPEED_MEDIUM, 500), (SPEED_FAST, 250))

  var altitudeMax = ALTITUDE_MAX_CURRENT
  var altitudeMin = ALTITUDE_MIN_CURRENT
  var outdoor: Boolean = false
  var speedVerticalIndoor = SPEED_VERTICAL_DEFAULT
  var speedVerticalOutdoor = SPEED_VERTICAL_DEFAULT
  val rDrone = new ScalaDrone()
  var timeRemaining = 0
  var angle = "0.1"
  var mode: Mode = Mode.BOOTSTRAP
  var battery = 0
  var altitude = 0f
  var yaw = 0f
  var isbatteryLow: Boolean = false
  var isBatteryHigh: Boolean = false
  var isFlying: Boolean = false
  var isEmergency: Boolean = false
  var isTrimSucceeded: Boolean = false
  var isVideoEnabled: Boolean = false
  var attering: Boolean = false;

  rDrone.addStatusChangeListener(this)
  rDrone.addNavDataListener(this)

  /**
   * interface dronestatusChangeListener
   * ready to send information to drone
   *
   */
  def ready() {
    rDrone.setConfigOption("control:euler_angle_max", angle)
    this.setAltitudeMax(2000)
    this.setSpeedHorizontal(200)
    rDrone.setCombinedYawMode(true);
    rDrone.trim();
  }

  /**
   * initialization of drone before takeOff
   *
   */
  def initialize() {
    rDrone.setConfigOption("control:euler_angle_max", this.angle)
    this.setAltitudeMax(2000)
    this.setSpeedHorizontal(200)
    this.setYaw(2f)
  }

  /**
   * Receive the navdata and update
   * the global var
   *
   * @param nd type navdata
   *
   */
  def navDataReceived(nd: NavData) {
    System.err.println(battery)
    battery = nd.getBattery()
    altitude = nd.getAltitude()
    mode = nd.getMode()
    isbatteryLow = nd.isBatteryTooLow()
    isBatteryHigh = nd.isBatteryTooHigh()
    isEmergency = nd.isEmergency()
    isFlying = nd.isFlying()
    isTrimSucceeded = nd.isTrimSucceeded()
    isVideoEnabled = nd.isVideoEnabled()
    this.setAltitudeIfBatteryLow()
  }

  /**
   * Connect to drone
   */
  def connect() {
    this.getDrone().connects()
    Thread sleep 500
    initialize()
  }

  /**
   * disconnect to drone
   */
  def disconnect() {
    this.getDrone().disconnects()
  }

  /**
   * land
   */
  def land() {
    this.getDrone().lands()
  }

  /**
   * takeOff with Altitude initialization before
   */
  def takeOffWithAltitude(altitude: Float) {
    initialize()
    Thread sleep 500
    this.getDrone().takeOffs()
    this.up(altitude)
  }

  /**
   * takeOff initialization before
   */
  def takeOff() {
    initialize()
    Thread sleep 500
    this.getDrone().takeOffs()
  }
  /**
   * hover initializa move with this values move(0,0,0,0)
   */
  def hover() {
    this.getDrone().hovers()
  }
  /**
   * trim
   */
  def trim() {
    this.getDrone().trims()
  }

  /**
   * calculate the equivalent arc by diametre
   * and
   * @return this value
   *
   */
  def calculArcCircl(diametre: Float): Float = {
    return (((3.14 * (diametre / 2))) / 5).toFloat
  }

  /**
   * return if the value is authorized
   *
   * @return exception is value unauthorized
   *
   */
  def isAuthorizedValue(min: Float, max: Float, value: Float): Boolean = {
    if (value >= min || value <= max) {
      return true
    } else
      return false
  }

  /**
   * Get the drone object
   * @return drone object
   */
  def getDrone() = rDrone

  /**
   * Return percentage battery remaining
   *
   * @return Int
   *
   */
  def getBatteryPercentage() = battery

  /**
   * Return minute battery remaining
   *
   * @return Int
   *
   */
  def getBatteryTimeRemaining() = (battery * 12) / 100

  /**
   * Return altitude in millimeter
   *
   * @return Int
   *
   */
  def getAltitude() = altitude * 1000

  /**
   * get the yaw
   *
   * return float yaw used
   *
   */
  def getYaw() = yaw

  /**
   * Convert degre to radian
   *
   * return float the radian value
   *
   */
  def degreeToRad(degre: Int): Float = {
    return ((degre * 0.7) / 40).toFloat
  }

  /**
   * Convert radian to Degree
   *
   * return int the degree value
   *
   */
  def radToDegre(radian: Float): Int = {
    return ((radian * 40) / 0.7).toInt
  }

  /**
   * return the time required for rotation by angle
   *
   */
  def getRotationTimeByAngle(angle: Int): Int = {
    return ((angle * 1000) / this.getYaw().toDegrees).toInt
  }

  /**
   * turn Left by degree
   *
   */
  def turnLeftByDegre(degre: Int) {
    var timer = new MyTimer(this.getRotationTimeByAngle(degre))
    while (!(timer isFinish)) {
      rDrone.turnLeft()
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * turn Right by degree
   *
   */
  def turnRightByDegre(degre: Int) {
    var timer = new MyTimer(this.getRotationTimeByAngle(degre))
    while (!(timer isFinish)) {
      rDrone.turnRight()
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * Recommanded values goes from 40/s to 350/s (approx 0.7rad/s to 6.11rad/s)
   *
   */
  def setYaw(yaw: Float) {
    this.yaw = yaw
    rDrone.setConfigOptions("control:control_yaw", yaw.toString())
  }

  /**
   * Set the vertical speed indoor
   * @param speed int
   * Recommanded values goes from 200 and 2000 milimeter by second
   */
  def setSpeedVerticalIndoor(speed: Int) {
    isAuthorizedValue(SPEED_VERTICAL_SPEED_MIN, SPEED_VERTICAL_SPEED_MAX, speed)
    rDrone.setConfigOptions("control:indoor_control_vz_max", speed toString ())
    speedVerticalIndoor = speed
  }

  /**
   * Set the vertical speed outdour
   * @param speed int contain speed between the predefine speed
   */
  def setSpeedVerticalOutdoor(speed: Int) {
    isAuthorizedValue(SPEED_VERTICAL_SPEED_MIN, SPEED_VERTICAL_SPEED_MAX, speed)
    rDrone.setConfigOptions("control:outdoor_control_vz_max", speed toString ())
    speedVerticalOutdoor = speed
  }

  /**
   * get the vertical speed indoor
   *
   * @return int
   *
   */
  def getSpeedVerticalIndoor() = speedVerticalIndoor

  /**
   * get the vertical speed outdoor
   *
   * @return int
   *
   */
  def getSpeedVerticalOutdoor() = speedVerticalOutdoor

  /**
   * get speed current by environment
   *
   * @return int
   *
   */
  def getSpeedVertical(): Int = {
    if (this.isOutdoor())
      return this.getSpeedVerticalOutdoor()
    else
      return this.getSpeedVerticalIndoor()
  }

  /**
   * get speed in relation to angle current
   *
   * @return int
   *
   */
  def getSpeed(): Int = {
    if (this.getListSpeed.exists((x) => x._2 == this.getSpeedHorizontal())) {
      return this.getListSpeed.filter((x) => x._2 == (this.getSpeedHorizontal()))(0)._1
    } else
      return 1

  }

  /**
   * Set the maximal altitude
   *
   * @param altitude Integer altitude in millimeter. Must be higher than minimal altitude
   *
   */
  def setAltitudeMax(altitude: Float) {
    if (isAuthorizedValue(ALTITUDE_MIN_CURRENT, ALTIUDE_MAX, altitude)) {
      rDrone.setConfigOptions("control:altitude_max", altitude toString ())
      altitudeMax = altitude
    } else {
      rDrone.setConfigOptions("control:altitude_mian", ALTITUDE_MAX_CURRENT toString ())
      altitudeMax = ALTITUDE_MAX_CURRENT
    }

  }

  /**
   * get the maximal altitude
   *
   * @return int maximal altitude in millimeter
   *
   */
  def getAltitudeMax() = altitudeMax

  /**
   * Set the minimal altitude
   *
   * @param altitude Integer altitude in millimeter. Must be lower than maximal altitude
   *
   */
  def setAltitudeMin(altitude: Float) {
    if (isAuthorizedValue(ALTITUDE_MIN_CURRENT, ALTITUDE_MAX_CURRENT, altitude)) {
      rDrone.setConfigOptions("control:altitude_min", altitude toString ())
      altitudeMin = altitude
    } else {
      rDrone.setConfigOptions("control:altitude_min", ALTITUDE_MIN_CURRENT toString ())
      altitudeMin = ALTITUDE_MIN_CURRENT
    }

  }

  /**
   * get the minimal altitude
   *
   * @return int minimal altitude in millimeter
   *
   */
  def getAltitudeMin() = altitudeMin

  /**
   * Down the drone to ALTITUDE_MAX_IF_LOW_BATTERY if
   * the battery is lower than the minimum require for
   * fly safely.
   * Down the drone to ALTITUDE_MAX_IF_VERY_LOW_BATTERY
   * if the battery is very low
   *
   */
  def setAltitudeIfBatteryLow() {
    if (this.getBatteryPercentage() < this.BATTERY_LOW / 2) {
      if (this.getAltitude() <= this.ALTITUDE_MAX_IF_VERY_LOW_BATTERY + ALTITUDE_MAX_IF_LOW_BATTERY_TOLERANCE) {
      } else {
        if (!isdowning)
          goTo(this.ALTITUDE_MAX_IF_VERY_LOW_BATTERY)
        this.setAltitudeMax(this.ALTITUDE_MAX_IF_VERY_LOW_BATTERY + ALTITUDE_MAX_IF_LOW_BATTERY_TOLERANCE)
        this.rDrone.playLed(LED.BLINK_RED, 2, 4)
      }
    } else if (this.getBatteryPercentage() < this.BATTERY_LOW) {

      if (this.getAltitude() <= (this.ALTITUDE_MAX_IF_LOW_BATTERY + this.ALTITUDE_MAX_IF_LOW_BATTERY_TOLERANCE)) {
      } else {
        if (!isdowning)
          goTo(this.ALTITUDE_MAX_IF_LOW_BATTERY)
        this.setAltitudeMax(this.ALTITUDE_MAX_IF_LOW_BATTERY + ALTITUDE_MAX_IF_LOW_BATTERY_TOLERANCE)
        this.rDrone.playLed(LED.BLINK_ORANGE, 2, 4)
      }
    }

  }

  /**
   * Set the horizontal speed
   *
   * @param speed Integer between 200 et 2000 (in millimeter)
   *
   */
  def setSpeedHorizontal(speed: Int) {
    if (this.getListSpeed.exists((x) => x._1 == speed)) {
      angle = this.getListSpeed.filter((x) => x._1 == (speed))(0)._2
    } else {
      angle = "0.1"
    }
  }

  /**
   * Get the horizontal speed
   *
   * @return int
   *
   */
  def getSpeedHorizontal() = angle

  /**
   * Set the outdoor environment
   *
   * @param active Boolean. True if outdoor and false if not
   *
   * *
   */
  def setEnvironmentOutdoor(active: Boolean) {
    rDrone.setConfigOptions("control:outdoor", active.toString())
    outdoor = active
  }

  /**
   * Set the indoor environment
   *
   * @param active Boolean. True if indoor and false if not
   *
   * *
   */
  def setEnvironmentIndoor(active: Boolean) {
    rDrone.setConfigOptions("control:outdoor", (!active).toString())
    outdoor = !active
  }

  /**
   * return List relative to environment
   * *
   */
  def getListSpeed(): List[(Int, String, Int)] = {
    if (isOutdoor()) {
      return this.speedListOutDoor

    } else return this.speedListInDoor

  }

  /**
   * Return if the drone is configured for outdoor
   *
   * @return boolean
   *
   */
  def isOutdoor() = outdoor

  /**
   * Return if the drone is configured for indoor
   *
   * @return boolean
   *
   */
  def isIndoor() = !outdoor

  /**
   * Return if the drone is flying between
   * Maximum et Minimum altitude
   *
   * @return boolean
   *
   */
  def isAuthorizedAltitude() = (this.getAltitude() <= this.getAltitudeMax()
    && this.getAltitude() >= this.getAltitudeMin())

  def getNbBoucle(distance: Float): Float = {
    var s = 0
    var speed = getSpeedHorizontal()

    if (speedListInDoor.exists((x) => x._2 == speed))
      s = speedListInDoor.filter((x) => x._2 == (speed))(0)._3
    else
      s = 1
    return (s * distance) / 1000
  }

  /**
   * move back the drone
   *
   * @param distance the distance in millimeter
   *
   */
  def backByDistance(distance: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      rDrone.back
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * move back the drone
   *
   * @param time in ms
   *
   */
  def backByTime(time: Int) {
    var timer = new MyTimer(time)
    while (!timer.isFinish) {
      rDrone back ()
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * move front the drone
   *
   * @param distance the distance in millimeter
   *
   */
  def forwardByDistance(distance: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      rDrone forward ()
      Thread sleep VAL_SLEEP
    }

  }

  /**
   * move front the drone
   *
   * @param time in ms
   *
   */
  def forwardByTime(time: Int) {
    var timer = new MyTimer(time)
    while (!timer.isFinish) {
      rDrone forward ()
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * Move the drone in altitude. (down or up)
   *
   * @param altitude altitude in  millimeter
   *
   */
  def goTo(altitude: Float) {
    this.getDrone().isEmergencyModes()
    if (this.getAltitude() > this.ALTITUDE_MIN_CURRENT && altitude < this.ALTITUDE_MAX_CURRENT) {
      if (altitude < this.getAltitude())
        this.down(altitude)
      else
        this.up(altitude)
    }
  }

  /**
   *  @param altitude altitude in  millimeter and verticalSpeed
   * @return how many milliseconds must put for do one meter
   */
  def getMiliSecSpeedVerticalByMeter(altitude: Float, verticalSpeedMax: Int): Int = {
    return (((altitude) / (verticalSpeedMax)) * 1000).toInt;

  }

  /**
   *  @param distance in Int
   * @return how many milliseconds must put for do one meter
   */
  def getMiliSecSpeedHorizontalByMeter(distance: Int): Int = {
    var time = 0
    if (this.speedByTime.exists((x) => x._1 == this.getSpeed())) {
      time = speedByTime.filter((x) => x._1 == (this.getSpeed()))(0)._2
      return time * distance
    } else
      return time

  }
  /**
   * Up altitude
   *
   * @param altitude this is the altitude in millimeter
   *
   */
  def up(altitude: Float) {
    var destination = this.getAltitude() + altitude
    while ((this.getAltitude() < destination)) {
      println("Altitude réél : " + this.getAltitude() + " Altitude voulu" + destination)
      this.getDrone().up()
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * Up altitude
   *
   * @param altitude this is the altitude in millimeter
   *
   */
  def upByDistance(altitude: Float) {
    var timer = new MyTimer(getMiliSecSpeedVerticalByMeter(altitude.toInt, this.getSpeedVertical()))
    println(getMiliSecSpeedVerticalByMeter(altitude.toInt, this.getSpeedVertical()))
    println("je suis l e vertical speed" + this.getSpeedVertical)
    while (!(timer isFinish)) {
      println("je up")
      rDrone.up()
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * up altitude
   *
   * @param millisecond time in millisecond
   *
   */

  def upByTime(millisecond: Int) {
    var timer = new MyTimer(millisecond)
    while (!(timer isFinish) && isAuthorizedAltitude) {
      rDrone up ()
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * down altitude
   *
   * @param altitude this is the altitude in millimeter
   *
   */
  def down(altitude: Float) {

    while ((this.getAltitude() > altitude)) {
      println("Altitude réél : " + this.getAltitude() + " Altitude voulu" + altitude)
      this.getDrone().down()
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * down altitude
   *
   * @param altitude this is the altitude in millimeter
   *
   */
  def downByDistance(altitude: Float) {
    isdowning = true
    System.err.println("getalt : " + this.getAltitude() + "parametre : " + altitude)
    while (this.getAltitude() > altitude) {
      rDrone down ()
      Thread sleep VAL_SLEEP
    }
    isdowning = false
  }

  /**
   * down altitude
   *
   * @param millisecond time in millisecond
   *
   */
  def downByTime(millisecond: Int) {
    var timer = new MyTimer(millisecond)
    while (!(timer isFinish) && isAuthorizedAltitude) {
      rDrone.down()
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * Move the drone to the left
   *
   * @param distance The distance in millimeter
   *
   */
  def moveLeftByDistance(distance: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      rDrone.moveLeft()
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * Move the drone to the left
   *
   * @param time The time in millisecond
   *
   */
  def moveLeftByTime(time: Int) {
    var timer = new MyTimer(time)
    while (!timer.isFinish) {
      rDrone.moveLeft()
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * Move the drone to the right
   *
   * @param distance The distance in millimeter
   *
   */
  def moveRightByDistance(distance: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      rDrone.moveRight()
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * Move the drone to the right
   *
   * @param time The time in millisecond
   *
   */
  def moveRightByTime(time: Int) {
    var timer = new MyTimer(time)
    while (!timer.isFinish) {
      rDrone.moveRight()
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * @parameter degree lower to 45°
   * calcul value equivalent for this degree
   * @return this value
   */
  def calculAngleInf45(deg: Float): Float = {
    return (deg * 2) / 90
  }

  /**
   * @parameter degree higher to 45°
   * calcul value equivalent for this degree
   * @return this value
   */
  def calculAngleSup45(deg: Float): Float = {
    return (deg * (deg / 100)) / 90
  }

  /**
   * @parameter distance and degree
   * diagonal Left Forward by Distance
   */
  def moveDiagonalLeftForward(distance: Float, deg: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(-this.calculAngleInf45(deg), -1, 0, 0)
        Thread sleep VAL_SLEEP
      } else if (deg <= 90 && deg > 45) {
        rDrone.moveDiagonal(-1, -this.calculAngleSup45(deg), 0, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Left Forward by Time
   */
  def moveDiagonalLeftForwardTime(time: Int, deg: Float) {
    var timer = new MyTimer(time)
    while (!timer.isFinish) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(-this.calculAngleInf45(deg), -1, 0, 0)
        Thread sleep VAL_SLEEP
      } else if (deg <= 90 && deg > 45) {
        rDrone.moveDiagonal(-1, -this.calculAngleSup45(deg), 0, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Right Forward by Distance
   */
  def moveDiagonalRightForward(distance: Float, deg: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(this.calculAngleInf45(deg), -1, 0, 0)
        Thread sleep VAL_SLEEP
      } else if (deg <= 90 && deg > 45) {
        rDrone.moveDiagonal(-1, this.calculAngleSup45(deg), 0, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Right Forward by Time
   */
  def moveDiagonalRightForwardTime(time: Int, deg: Float) {
    var timer = new MyTimer(time)
    while (!timer.isFinish) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(this.calculAngleInf45(deg), -1, 0, 0)
        Thread sleep VAL_SLEEP
      } else if (deg <= 90 && deg > 45) {
        rDrone.moveDiagonal(-1, this.calculAngleSup45(deg), 0, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Left Back by Distance
   */
  def moveDiagonalLeftBack(distance: Float, deg: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(-this.calculAngleInf45(deg), 1, 0, 0)
        Thread sleep VAL_SLEEP
      } else if (deg <= 90 && deg > 45) {
        rDrone.moveDiagonal(-1, this.calculAngleSup45(deg), 0, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Left Back by Time
   */
  def moveDiagonalLeftBackTime(time: Int, deg: Float) {
    var timer = new MyTimer(time)
    while (!timer.isFinish) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(-this.calculAngleInf45(deg), 1, 0, 0)
        Thread sleep VAL_SLEEP
      } else if (deg <= 90 && deg > 45) {
        rDrone.moveDiagonal(-1, this.calculAngleSup45(deg), 0, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }
  /**
   * @parameter distance and degree
   * diagonal Right Back by Distance
   */
  def moveDiagonalRightBack(distance: Float, deg: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(this.calculAngleInf45(deg), 1, 0, 0)
        Thread sleep VAL_SLEEP
      } else if (deg <= 90 && deg > 45) {
        rDrone.moveDiagonal(1, this.calculAngleSup45(deg), 0, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Right Back by Time
   */
  def moveDiagonalRightBackTime(time: Int, deg: Float) {
    var timer = new MyTimer(time)
    while (!timer.isFinish) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(this.calculAngleInf45(deg), 1, 0, 0)
        Thread sleep VAL_SLEEP
      } else if (deg <= 90 && deg > 45) {
        rDrone.moveDiagonal(1, this.calculAngleSup45(deg), 0, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Forward Up by Distance
   */
  def moveDiagonalForwardUpByDistance(distance: Float, deg: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      if (isAuthorizedAltitude && (0 <= deg && deg <= 45)) {
        rDrone.moveDiagonal(0, -1, this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (isAuthorizedAltitude && (deg <= 90 && deg > 45)) {
        rDrone.moveDiagonal(0, -this.calculAngleInf45(deg), 1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Forward Up by Time
   */
  def moveDiagonalForwardUp(time: Int, deg: Float) {
    var timer = new MyTimer(time)
    while (!timer.isFinish && isAuthorizedAltitude) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(0, -1, this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (deg <= 90 && deg > 45) {
        rDrone.moveDiagonal(0, -this.calculAngleInf45(deg), 1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Forward Down by Distance
   */
  def moveDiagonalForwardDownByDistance(distance: Float, deg: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      if (isAuthorizedAltitude && (0 <= deg && deg <= 45)) {
        rDrone.moveDiagonal(0, -1, -this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (isAuthorizedAltitude && (deg <= 90 && deg > 45)) {
        rDrone.moveDiagonal(0, -this.calculAngleInf45(deg), -1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Forward Down by Time
   */
  def moveDiagonalForwardDown(time: Int, deg: Float) {
    var timer = new MyTimer(time)
    while (!timer.isFinish && isAuthorizedAltitude) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(0, -1, -this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (isAuthorizedAltitude && (deg <= 90 && deg > 45)) {
        rDrone.moveDiagonal(0, -this.calculAngleInf45(deg), -1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Back Up by Distance
   */
  def moveDiagonalBackUpByDistance(distance: Float, deg: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      if (isAuthorizedAltitude && (0 <= deg && deg <= 45)) {
        rDrone.moveDiagonal(0, 1, this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (isAuthorizedAltitude && (deg <= 90 && deg > 45)) {
        rDrone.moveDiagonal(0, this.calculAngleInf45(deg), 1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Back Up by Time
   */
  def moveDiagonalBackUp(time: Int, deg: Float) {
    var timer = new MyTimer(time)
    while (!timer.isFinish && isAuthorizedAltitude) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(0, 1, this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (isAuthorizedAltitude && (deg <= 90 && deg > 45)) {
        rDrone.moveDiagonal(0, this.calculAngleInf45(deg), 1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Back Down by Distance
   */
  def moveDiagonalBackDownByDistance(distance: Float, deg: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      if (isAuthorizedAltitude && (0 <= deg && deg <= 45)) {
        rDrone.moveDiagonal(0, 1, -this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (isAuthorizedAltitude && (deg <= 90 && deg > 45)) {
        rDrone.moveDiagonal(0, this.calculAngleInf45(deg), -1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Back Down by Time
   */
  def moveDiagonalBackDown(time: Int, deg: Float) {
    var timer = new MyTimer(time)
    while (!timer.isFinish && isAuthorizedAltitude) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(0, 1, -this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (isAuthorizedAltitude && (deg <= 90 && deg > 45)) {
        rDrone.moveDiagonal(0, this.calculAngleInf45(deg), -1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Right Down by Distance
   */
  def moveDiagonalRightDownByDistance(distance: Float, deg: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      if (isAuthorizedAltitude && (0 <= deg && deg <= 45)) {
        rDrone.moveDiagonal(1, 0, -this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (isAuthorizedAltitude && (deg <= 90 && deg > 45)) {
        rDrone.moveDiagonal(this.calculAngleInf45(deg), 0, -1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Right Down by Time
   */
  def moveDiagonalRightDown(time: Int, deg: Float) {
    var timer = new MyTimer(time)
    while (!timer.isFinish && isAuthorizedAltitude) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(1, 0, -this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (isAuthorizedAltitude && (deg <= 90 && deg > 45)) {
        rDrone.moveDiagonal(this.calculAngleInf45(deg), 0, -1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Right Up by Distance
   */
  def moveDiagonalRightUpByDistance(distance: Float, deg: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      if (isAuthorizedAltitude && (0 <= deg && deg <= 45)) {
        rDrone.moveDiagonal(1, 0, this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (isAuthorizedAltitude && (deg <= 90 && deg > 45)) {
        rDrone.moveDiagonal(this.calculAngleInf45(deg), 0, 1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Right Up by Time
   */
  def moveDiagonalRightUp(time: Int, deg: Float) {
    var timer = new MyTimer(time)
    while (!timer.isFinish && isAuthorizedAltitude) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(1, 0, this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (isAuthorizedAltitude && (deg <= 90 && deg > 45)) {
        rDrone.moveDiagonal(this.calculAngleInf45(deg), 0, 1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Left Down by Distance
   */
  def moveDiagonalLeftDownByDistance(distance: Float, deg: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      if (isAuthorizedAltitude && (0 <= deg && deg <= 45)) {
        rDrone.moveDiagonal(-1, 0, -this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (isAuthorizedAltitude && (deg <= 90 && deg > 45)) {
        rDrone.moveDiagonal(-this.calculAngleInf45(deg), 0, -1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Left Down by Time
   */
  def moveDiagonalLeftDown(time: Int, deg: Float) {
    var timer = new MyTimer(time)
    while (!timer.isFinish && isAuthorizedAltitude) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(-1, 0, -this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (isAuthorizedAltitude && (deg <= 90 && deg > 45)) {
        rDrone.moveDiagonal(-this.calculAngleInf45(deg), 0, -1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Left Up by Distance
   */
  def moveDiagonalLeftUpByDistance(distance: Float, deg: Float) {
    for (i <- 0 to this.getNbBoucle(distance).round) {
      if (isAuthorizedAltitude && (0 <= deg && deg <= 45)) {
        rDrone.moveDiagonal(-1, 0, +this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (isAuthorizedAltitude && (deg <= 90 && deg > 45)) {
        rDrone.moveDiagonal(-this.calculAngleInf45(deg), 0, +1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * @parameter distance and degree
   * diagonal Left Up by Time
   */
  def moveDiagonalLeftUp(time: Int, deg: Float) {
    var timer = new MyTimer(time)
    while (!timer.isFinish && isAuthorizedAltitude) {
      if (0 <= deg && deg <= 45) {
        rDrone.moveDiagonal(-1, 0, +this.calculAngleInf45(deg), 0)
        Thread sleep VAL_SLEEP
      } else if (isAuthorizedAltitude && (deg <= 90 && deg > 45)) {
        rDrone.moveDiagonal(-this.calculAngleInf45(deg), 0, +1, 0)
        Thread sleep VAL_SLEEP
      }
    }
  }

  /**
   * do semicircle
   * @parameter diameter for semicircle
   */
  def semiCerclRightForward(diametre: Float) {
    this.moveRightByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalRightForward(this.calculArcCircl(diametre), 45)
    this.forwardByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalLeftForward(this.calculArcCircl(diametre), 45)
    this.moveLeftByDistance(this.calculArcCircl(diametre))
  }

  /**
   * do semicircle
   * @parameter diameter for semicircle
   */
  def semiCerclLeftForward(diametre: Float) {
    this.moveLeftByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalLeftForward(this.calculArcCircl(diametre), 45)
    this.forwardByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalRightForward(this.calculArcCircl(diametre), 45)
    this.moveRightByDistance(this.calculArcCircl(diametre))
  }

  /**
   * do semicircle
   * @parameter diameter for semicircle
   */
  def semiCerclRightBack(diametre: Float) {
    this.moveRightByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalRightBack(this.calculArcCircl(diametre), 45)
    this.backByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalLeftBack(this.calculArcCircl(diametre), 45)
    this.moveLeftByDistance(this.calculArcCircl(diametre))
  }

  /**
   * do semicircle
   * @parameter diameter for semicircle
   */
  def semiCerclLeftBack(diametre: Float) {
    this.moveLeftByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalLeftBack(this.calculArcCircl(diametre), 45)
    this.backByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalRightBack(this.calculArcCircl(diametre), 45)
    this.moveRightByDistance(this.calculArcCircl(diametre))
  }

  /**
   * do semicircle
   * @parameter diameter for semicircle
   */
  def semiCerclLeftUp(diametre: Float) {
    this.moveLeftByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalLeftUpByDistance(this.calculArcCircl(diametre), 45)
    this.upByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalRightUpByDistance(this.calculArcCircl(diametre), 45)
    this.moveRightByDistance(this.calculArcCircl(diametre))
  }

  /**
   * do semicircle
   * @parameter diameter for semicircle
   */
  def semiCerclLeftDown(diametre: Float) {
    this.moveLeftByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalLeftDownByDistance(this.calculArcCircl(diametre), 45)
    this.downByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalRightDownByDistance(this.calculArcCircl(diametre), 45)
    this.moveRightByDistance(this.calculArcCircl(diametre))
  }

  /**
   * do semicircle
   * @parameter diameter for semicircle
   */
  def semiCerclRightUp(diametre: Float) {
    this.moveRightByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalRightUpByDistance(this.calculArcCircl(diametre), 45)
    this.upByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalLeftUpByDistance(this.calculArcCircl(diametre), 45)
    this.moveLeftByDistance(this.calculArcCircl(diametre))
  }

  /**
   * do semicircle
   * @parameter diameter for semicircle
   */
  def semiCerclRightDown(diametre: Float) {
    this.moveRightByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalRightDownByDistance(this.calculArcCircl(diametre), 45)
    this.downByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalLeftDownByDistance(this.calculArcCircl(diametre), 45)
    this.moveLeftByDistance(this.calculArcCircl(diametre))
  }

  /**
   * do semicircle
   * @parameter diameter for semicircle
   */
  def semiCerclForwardLeft(diametre: Float) {
    this.forwardByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalLeftForward(this.calculArcCircl(diametre), 45)
    this.moveLeftByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalLeftBack(this.calculArcCircl(diametre), 45)
    this.backByDistance(this.calculArcCircl(diametre))

  }

  /**
   * do semicircle
   * @parameter diameter for semicircle
   */
  def semiCerclForwardRight(diametre: Float) {
    this.forwardByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalRightForward(this.calculArcCircl(diametre), 45)
    this.moveRightByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalRightBack(this.calculArcCircl(diametre), 45)
    this.backByDistance(this.calculArcCircl(diametre))
  }

  /**
   * do semicircle
   * @parameter diameter for semicircle
   */
  def semiCerclBackLeft(diametre: Float) {
    this.backByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalLeftBack(this.calculArcCircl(diametre), 45)
    this.moveLeftByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalLeftForward(this.calculArcCircl(diametre), 45)
    this.forwardByDistance(this.calculArcCircl(diametre))

  }

  /**
   * do semicircle
   * @parameter diameter for semicircle
   */
  def semiCerclBackRight(diametre: Float) {
    this.backByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalRightBack(this.calculArcCircl(diametre), 45)
    this.moveRightByDistance(this.calculArcCircl(diametre))
    this.moveDiagonalRightForward(this.calculArcCircl(diametre), 45)
    this.forwardByDistance(this.calculArcCircl(diametre))
  }

  /**
   * do animation
   */
  def doMovementEight(diametre: Float) {
    this.semiCerclForwardRight(diametre)
    this.semiCerclBackLeft(diametre)
    this.semiCerclBackRight(diametre)
    this.semiCerclForwardLeft(diametre)
  }

  /**
   * do Circle  horizontal Forward Right Back Left By diametre
   */
  def doCircleHorizontal(diametre: Float) {
    this.semiCerclForwardRight(diametre)
    this.semiCerclBackLeft(diametre)
  }

  /**
   * do Circle  Vertical Forward Right Back Left By diametre
   */
  def doCircleVertical(diametre: Float) {
    this.semiCerclRightUp(diametre)
    this.semiCerclLeftDown(diametre)
  }

  /**
   * turn Left and Up at the same time by Time
   * @param time in Int
   */
  def turnLeftUp(time: Int) {
    var timer = new MyTimer(time)
    while (!timer.isFinish && isAuthorizedAltitude) {
      rDrone.moves(0, 0, 1, -1)
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * turn Right and Up at the same time by Time
   * @param time in Int
   */
  def turnRightUp(time: Int) {
    var timer = new MyTimer(time)
    while (!timer.isFinish && isAuthorizedAltitude) {
      rDrone.moves(0, 0, 1, 1)
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * turn Left and Down at the same time by Time
   * @param time in Int
   */
  def turnLeftDown(time: Int) {
    var timer = new MyTimer(time)
    while (!timer.isFinish && isAuthorizedAltitude) {
      rDrone.moves(0, 0, -1, -1)
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * turn Right and Down at the same time by Time
   * @param time in Int
   */
  def turnRightDown(time: Int) {
    var timer = new MyTimer(time)
    while (!timer.isFinish && isAuthorizedAltitude) {
      rDrone.moves(0, 0, -1, 1)
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * do animation butterfly Left Right by Time
   * @param time in Int
   */
  def doMovementButterflyLeftRight(time: Int) {
    var timer = new MyTimer(time)
    while (!timer.isFinish) {
      this.moveLeftByTime(10)
      this.moveRightByTime(10)
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * do animation butterfly Forward Back by Time
   * @param time in Int
   */
  def doMovementButterflyForwardBack(time: Int) {
    var timer = new MyTimer(time)
    while (!timer.isFinish) {
      this.forwardByTime(10)
      this.backByTime(10)
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * do animation butterfly diagonal Forward Right by Time
   * @param time in Int
   */
  def doMovementButterFlyDiagonalForwardRight(time: Int, angle: Float) {
    var timer = new MyTimer(time)
    while (!timer.isFinish) {
      this.moveDiagonalRightForwardTime(10, angle)
      this.moveDiagonalLeftBackTime(10, angle)
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * do animation butterfly diagonal Forward Left by Time
   * @param time in Int
   */
  def doMovementButterFlyDiagonalForwardLeft(time: Int, angle: Float) {
    var timer = new MyTimer(time)
    while (!timer.isFinish) {
      this.moveDiagonalLeftForwardTime(10, angle)
      this.moveDiagonalRightBackTime(10, angle)
      Thread sleep VAL_SLEEP
    }
  }

  /**
   * do square Vertical  by distance
   * @param distance Int
   */
  def squareVertical(distance: Int) {
    this.up(distance)
    this.moveLeftByDistance(distance)
    this.down(distance)
    this.moveRightByDistance(distance)
  }

  /**
   * do square Horizontal by distance
   * @param distance Int
   */
  def squareHorizontal(distance: Int) {
    this.forwardByDistance(distance)
    this.moveLeftByDistance(distance)
    this.backByDistance(distance)
    this.moveRightByDistance(distance)
  }
  
   /**
   * move Forward by distance, Turn Around and move Forward by distance
   * @param distance Int
   */
  def doForwardTurnAroundForward(distance:Float){
    this.forwardByDistance(distance)
    this.turnLeftByDegre(180)
    this.forwardByDistance(distance)
  }

  def animationSimple() {
    this.forwardByDistance(2000)
    this.turnLeftByDegre(90)
    this.forwardByDistance(1000)
    this.turnLeftByDegre(90)
    this.upByDistance(1500)
    this.turnRightByDegre(270)
    this.forwardByDistance(1000)
    this.turnRightByDegre(90)
    this.moveDiagonalForwardDown(1000, 20)
    this.turnRightUp(1000)

  }
  def simple() {
    this.forwardByDistance(500)
    Thread sleep 1000
    this.backByDistance(500)
    Thread sleep 1000
    this.forwardByDistance(250)
    Thread sleep 1500
    this.turnLeftByDegre(90)
    Thread sleep 200
    this.turnRightByDegre(180)
  }
  
  
}


object Drone {
  def main(args: Array[String]) {
    val drone = new Drone()
  

        for (i <- 0 to drone.getClass().getMethods().size - 1) {
       //System.out.println("case \"" + drone.getClass().getMethods()(i).toString().split("\\.")(2).split("\\(")(0) + "\" => " + "" + drone.getClass().getMethods()(i).toString().split("\\.")(2).split("\\(")(0) + "()")
          if(drone.getClass().getMethods()(i).toString().split("\\.")(2).split("\\(")(0)!=null){
             println( drone.getClass().getMethods()(i).toString().split("\\.")(2))
          }
          else 
          {
            
          }
        }

  }
}