import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.TextArea

class AllAppointmentsDoc extends Initializable {

  @FXML
  private var appointments_text: TextArea = _

  private val usr = FxApp.user

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    val apts = FxApp.mf1.filterAppointmentsDoc(FxApp.user).sortWith((a, b) => Calendar.isFirst(a._2, b._2))
    val aptms2 = FxApp.mf2.filterAppointmentsDoc(FxApp.user).sortWith((a, b) => Calendar.isFirst(a._2, b._2))
    if (apts.nonEmpty) {
      apts.sortWith((x,y) => Calendar.isFirst(x._2, y._2))
      appointments_text.setText("Next Appointments: " + "\n")
      apts.foreach(a => {
        appointments_text.appendText("Date: " + Calendar.toString(a._2) + " - " + "Patient name: " + a._1._1 + " - " + " Birthdate: " + Calendar.toString(a._1._2))
        appointments_text.appendText("\n\n")
      })
    }
    if (aptms2.nonEmpty) {
      appointments_text.setText("Next Appointments: " + "\n")
      aptms2.foreach(a => {
        appointments_text.appendText("Date: " + Calendar.toString(a._2) + " - " + "Patient name: " + a._1._1 + " - " + " Birthdate: " + Calendar.toString(a._1._2))
        appointments_text.appendText("\n\n")
      })
    }
    if (!apts.nonEmpty && !aptms2.nonEmpty) appointments_text.setText("No Appointments Scheduled for you... " + "\n")
  }
}
