import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.TextArea

class AllExamsTodayDoc extends Initializable {

  @FXML
  private var exams_text: TextArea = _

  private val mf1 = FxApp.mf1
  private val mf2 = FxApp.mf2
  private val usr = FxApp.user
  private val today = Calendar.getCurrentTime()


  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    val exs = mf1.filterExamPatientDateF(today, usr)
    val exs2 = mf2.filterExamPatientDateF(today, usr)
    if (exs.nonEmpty) {
      exams_text.setText("Next Exams: " + "\n")
      exs.foreach(a => {
        exams_text.appendText("Date: " + Calendar.toString(a._4) + " - " + "Patient name: " + a._1._1 + " - " + " Birthdate: " + Calendar.toString(a._1._2))
        exams_text.appendText("\n")
      })
    }
    if (exs2.nonEmpty) {
      exams_text.setText("Next Exams: " + "\n")
      exs2.foreach(a => {
        exams_text.appendText("Date: " + Calendar.toString(a._4) + " - " + "Patient name: " + a._1._1 + " - " + " Birthdate: " + Calendar.toString(a._1._2))
        exams_text.appendText("\n")
      })
    }
    if(!exs.nonEmpty && !exs2.nonEmpty) exams_text.setText("No Exams Scheduled for you... " + "\n")
  }
}
