import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.TextArea

class AllAppointmentsTodayDoc extends Initializable {

  @FXML
  private var appointments_text: TextArea = _

  private val mf1 = FxApp.mf1
  private val mf2 = FxApp.mf2

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    val exs = mf1.filterAppointmentsDocDate(FxApp.user, FxApp.today)
    val exs2 = mf2.filterAppointmentsDocDate(FxApp.user, FxApp.today)
    if (exs.nonEmpty) {
      appointments_text.setText("Next Appointments: " + "\n")
      exs.foreach(a => {
        appointments_text.appendText("Date: " + Calendar.toString(a._2) + "\n" + "Patient name: " + a._1._1 + "\n" + " Birthdate: " + Calendar.toString(a._1._2))
        appointments_text.appendText("\n")
      })
    }
    if (exs2.nonEmpty) {
      appointments_text.setText("Next Appointments: " + "\n")
      exs2.foreach(a => {
        appointments_text.appendText("Date: " + Calendar.toString(a._2) + "\n" + "Patient name: " + a._1._1 + "\n" + " Birthdate: " + Calendar.toString(a._1._2))
        appointments_text.appendText("\n")
      })
    }
    if(!exs.nonEmpty && !exs2.nonEmpty) appointments_text.setText("No Appointments Scheduled for you... " + "\n")
  }
}
