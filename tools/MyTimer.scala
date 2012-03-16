package tools
import java.util.Timer
import java.util.TimerTask
class MyTimer(time: Int) extends Timer {
  var task = new Task()
  schedule(task, time);
  class Task() extends TimerTask {
    var bool = false
    def run(): Unit = {
      bool = true
      this cancel ()
    }
    def getBool = bool
  }

  def isFinish = task getBool
}

