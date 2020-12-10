import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.TextArea

class NextAppointmentDoc extends Initializable {

  @FXML
  private var appointment_text: TextArea = _

  private val mf1 = FxApp.mf1
  private val mf2 = FxApp.mf2
  private val usr = FxApp.user

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    val exl = mf1.filterAppointmentsDoc(usr).sortWith((a, b) => Calendar.isFirst(a._2, b._2))
    val exl2 = mf2.filterAppointmentsDoc(usr).sortWith((a, b) => Calendar.isFirst(a._2, b._2))

    if(exl.length > 0) {
      val ex = exl.head
      appointment_text.appendText("Next Appointment: " + "\n")
      appointment_text.appendText("Date and Time: " + Calendar.toString(ex._2) + "\n")
      appointment_text.appendText("Patient: " + ex._1._1 + " - " + Calendar.toString(ex._1._2) + "\n")
    }
    if(exl2.length > 0) {
      val ex2 = exl2.head
      appointment_text.appendText("Next Appointment: " + "\n")
      appointment_text.appendText("Date and Time: " + Calendar.toString(ex2._2) + "\n")
      appointment_text.appendText("Patient: " + ex2._1._1 + " - " + Calendar.toString(ex2._1._2) + "\n")
    }

    if(exl.length == 0 && exl2.length == 0) {
      appointment_text.setText("No appointments scheduled for the near future...")
    }
  }
}
