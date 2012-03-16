package test
import java.awt.event.ActionListener
import javax.swing.JFrame
import javax.swing.JCheckBox
import javax.swing.JButton
import javax.swing.JPanel
import java.awt.GridLayout
import scala.swing.event.ActionEvent
import com.codeminders.ardrone.NavData
import javax.swing.JTextField
import javax.swing.Timer
import java.awt.Color
import javax.swing.JLabel
import javax.swing.JRadioButton
import scala.dbc.result.Tuple
import scaladrone._

class Interf extends JFrame with ActionListener {
  var tmr = new Timer(1000, this)
  val distance = new JTextField("10")
  val angle = new JTextField("0.1")
  var drone = new Drone()
  var panelPrincipale = new JPanel()
  var panelNord = new JPanel()
  var panelVitesse = new JPanel()
  var panel1 = new JPanel()
  var panelTakeOffLand = new JPanel()
  var panelSaisie = new JPanel()
  var panel4 = new JPanel()
  var BTconnect = new JButton("Connect")
  var BTdisconnect = new JButton("Disconnect")
  var BTfront = new JButton("Front")
  var BTback = new JButton("Back")
  var BTup = new JButton("up")
  var BTdown = new JButton("down")
  var BTleft = new JButton("left")
  var BTright = new JButton("right")
  var BTtakeOff = new JButton("takeOff")
  var BTland = new JButton("land")
  var BTdiagFrontLeft = new JButton("diagFrontLeft")
  var BTdiagFrontRight = new JButton("diagFrontRight")
  var BTdiagBackLeft = new JButton("diagBackLeft")
  var BTdiagBackRight = new JButton("diagBackRight")
  var slow = new JRadioButton("slow")
  var medium = new JRadioButton("medium")
  var fast = new JRadioButton("fast")
  var BTdiagUpLeft = new JButton("diag\\")
  var BTdiagUpRight = new JButton("diag/")
  var BTdiagDownLeft = new JButton("diag/")
  var BTdiagDownRight = new JButton("diag\\")
  var BTturnLeft = new JButton("TLeft")
  var BTturnRight = new JButton("TRight")
  var BTappliquer = new JButton("Applicatiopackage gardening.fruitsn")
  var BTsemiCircle = new JButton("turn Vitesse")
  var BTtest = new JButton("le turn by degre")
  var degre = new JTextField("yaw");
  var diametre = new JTextField("degre");
  var BTbutterfly = new JButton("Butterfly")

  degre.setBackground(Color.RED)
  this.setSize(500, 500)
  this.setVisible(true)
  this.add(panelPrincipale)

  panelTakeOffLand.setBackground(Color.WHITE)
  panelPrincipale.setBackground(Color.WHITE)
  panelVitesse.setBackground(Color.WHITE)
  panelPrincipale.setLayout(new GridLayout(10, 3))
  panelVitesse.setLayout(new GridLayout(1, 3))
  panel1.setLayout(new GridLayout(2, 2))
  panelTakeOffLand.setLayout(new GridLayout(2, 1))
  panelSaisie.setLayout(new GridLayout(2, 2))
  panelSaisie.add(new JLabel("Distance"))
  panelSaisie.add(distance)
  panelSaisie.add(new JLabel("Angle"))
  panelSaisie.add(angle)
  panelPrincipale.add(BTconnect)
  panelPrincipale.add(panelTakeOffLand)
  panelPrincipale.add(BTdisconnect)
  panelPrincipale.add(slow)
  panelPrincipale.add(medium)
  panelPrincipale.add(fast)
  panelPrincipale.add(new JPanel())
  panelPrincipale.add(BTfront)
  panelPrincipale.add(new JPanel())
  panelPrincipale.add(BTdiagFrontLeft)
  panelPrincipale.add(BTup)
  panelPrincipale.add(BTdiagFrontRight)
  panelPrincipale.add(BTleft)
  panelPrincipale.add(panel1)
  panelPrincipale.add(BTright)
  panelPrincipale.add(BTdiagBackLeft)
  panelPrincipale.add(BTdown)
  panelPrincipale.add(BTdiagBackRight)
  panel1.add(BTdiagUpLeft)
  panel1.add(BTdiagUpRight)
  panel1.add(BTdiagDownLeft)
  panel1.add(BTdiagDownRight)
  panelPrincipale.add(BTturnLeft)
  panelPrincipale.add(BTback)
  panelPrincipale.add(BTturnRight)
  panelTakeOffLand.add(BTtakeOff)
  panelTakeOffLand.add(BTland)
  panelPrincipale.add(panelSaisie)
  panelPrincipale.add(BTappliquer)
  panelPrincipale.add(BTsemiCircle)
  panelPrincipale.add(BTtest)
  panelPrincipale.add(degre)
  diametre.setBackground(Color.ORANGE)
  panelPrincipale.add(diametre)
  panelPrincipale.add(BTbutterfly)
  BTconnect.addActionListener(this)
  BTdisconnect.addActionListener(this)
  BTdiagFrontLeft.addActionListener(this)
  BTup.addActionListener(this)
  BTdiagFrontRight.addActionListener(this)
  BTleft.addActionListener(this)
  BTright.addActionListener(this)
  BTdown.addActionListener(this)
  BTdiagBackLeft.addActionListener(this)
  BTdiagBackRight.addActionListener(this)
  BTland.addActionListener(this)
  BTtakeOff.addActionListener(this)
  BTdiagUpLeft.addActionListener(this)
  BTdiagUpRight.addActionListener(this)
  BTdiagDownLeft.addActionListener(this)
  BTdiagDownRight.addActionListener(this)
  BTtakeOff.addActionListener(this)
  BTland.addActionListener(this)
  medium.addActionListener(this)
  fast.addActionListener(this)
  slow.addActionListener(this)
  BTfront.addActionListener(this)
  BTback.addActionListener(this)
  BTturnLeft.addActionListener(this)
  BTturnRight.addActionListener(this)
  BTappliquer.addActionListener(this)
  BTsemiCircle.addActionListener(this)
  BTtest.addActionListener(this)
  BTbutterfly.addActionListener(this)

  tmr.start()

  def updateBattery(): Int = {
    System.err.print(drone.battery)
    return drone.battery
  }

  override def actionPerformed(e: java.awt.event.ActionEvent) {
    if (slow isSelected ()) {
      drone setSpeedHorizontal (1)
    }

    if (medium isSelected ()) {
      drone setSpeedHorizontal (2)
    }

    if (fast isSelected ()) {
      drone setSpeedHorizontal (3)
    }

    if (e.getSource().equals(BTconnect)) {
      drone.connect()
      System.err.println(drone.battery)
    }

    if (e.getSource().equals(tmr)) {
      this.setTitle("Battery : " + drone.battery + " Altitude :  " + drone.altitude + " - Temps restant : " + drone.getBatteryTimeRemaining())
    }

    if (e.getSource().equals(BTdisconnect)) {
      drone.getDrone().disconnects()

    }

    if (e.getSource().equals(BTfront)) {
      drone.setSpeedHorizontal(1)
      drone.forwardByDistance(Integer.parseInt(distance.getText()))
    }
    if (e.getSource().equals(BTback)) {
      drone.backByDistance(Integer.parseInt(distance.getText()))

    }

    if (e.getSource().equals(BTup)) {
      drone.getDrone().up()
    }

    if (e.getSource().equals(BTdown)) {
      drone.getDrone().down()
    }

    if (e.getSource().equals(BTleft)) {
      drone.moveLeftByDistance(Integer.parseInt(distance.getText()))
    }

    if (e.getSource().equals(BTright)) {
      drone.moveRightByDistance(Integer.parseInt(distance.getText()))
    }

    if (e.getSource().equals(BTdiagFrontLeft)) {
      drone.moveDiagonalLeftForward(Integer.parseInt(distance.getText()), 45)
    }

    if (e.getSource().equals(BTdiagFrontRight)) {
      drone.moveDiagonalRightForward(Integer.parseInt(distance.getText()), 45)
    }

    if (e.getSource().equals(BTdiagBackLeft)) {
      drone.moveDiagonalLeftBack(Integer.parseInt(distance.getText()), 45)

    }
    if (e.getSource().equals(BTdiagBackRight)) {
      drone.moveDiagonalRightBack(Integer.parseInt(distance.getText()), 45)
    }

    if (e.getSource().equals(BTdiagUpRight)) {
      drone.moveDiagonalRightUpByDistance(Integer.parseInt(distance.getText()), 45)
    }

    if (e.getSource().equals(BTdiagUpLeft)) {
      drone.getDrone().moveDiagonalUpLeft()
    }
    if (e.getSource().equals(BTdiagDownRight)) {
      drone.getDrone().moveDiagonalDownRight()
    }
    if (e.getSource().equals(BTdiagDownLeft)) {
      drone.getDrone().moveDiagonalDownLeft()
    }

    if (e.getSource().equals(BTland)) {
      drone.getDrone().land()

    }

    if (e.getSource().equals(BTtakeOff)) {
      drone.getDrone().leAllTakeOff()
    }

    if (e.getSource().equals(BTturnLeft)) {
      drone.getDrone().left
    }

    if (e.getSource().equals(BTturnRight)) {
      drone.getDrone().right()
    }

    if (e.getSource().equals(BTsemiCircle)) {
      drone.setYaw(this.degre.getText().toFloat)
    }

    if (e.getSource().equals(BTappliquer)) {
      drone.getDrone().setConfigOption("control:euler_angle_max", angle.getText())
    }

    if (e.getSource().equals(BTtest)) {
      //drone.doMovementEight(this.diametre.getText().toFloat)
      //  drone.semiCerclBackRight(this.diametre.getText().toFloat)
      drone.up(this.diametre.getText().toFloat)
    }

    if (e.getSource().equals(BTbutterfly)) {
      // drone.doMovementButterfly(this.diametre.getText().toInt)
    }
  }
}
object Interf {
  def main(args: Array[String]) {
    new Interf()
  }

}