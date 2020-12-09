import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.Parent
import javafx.scene.control.{Label, TextArea}

class HistoryControllerDoc extends Initializable {

  @FXML
  private var logout_button: Label = _

  @FXML
  private var home_button: Label = _

  @FXML
  private var exams_button: Label = _

  @FXML
  private var appointments_button: Label = _

  @FXML
  private var text_area: TextArea = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    val pal1 = FxApp.mf1.getAllAppointmentsDocPAL(FxApp.user)
    val pexl1 = FxApp.mf1.filterExamDocP(FxApp.user)
    val prescl1 = FxApp.mf1.getDoctorPrescription(FxApp.user)

    val pal2 = FxApp.mf2.getAllAppointmentsDocPAL(FxApp.user)
    val pexl2 = FxApp.mf2.filterExamDocP(FxApp.user)
    val prescl2 = FxApp.mf2.getDoctorPrescription(FxApp.user)

    if(FxApp.mf1.containsPersonDoctor(FxApp.user)) {
      text_area.appendText("Appointments: \n")
      pal1.foreach(a => {
        text_area.appendText("Date: " + Calendar.toString(a._2) + " - " + "Patient name: " + a._1._1 + " - " + " Birthdate: " + Calendar.toString(a._1._2) + "\n")
      })
      text_area.appendText("\n")
      text_area.appendText("Exams: \n")
      pexl1.foreach(e => {
        val tmp = e._6
        tmp match {
          case None => text_area.appendText("Date: " + Calendar.toString(e._4) + " - " + "Patient name: " + e._1._1 + " - " + " Birthdate: " + Calendar.toString(e._1._2) + " - Result: no result available" + "\n")
          case _ => text_area.appendText("Date: " + Calendar.toString(e._4) + " - " + "Patient name: " + e._1._1 + " - " + " Birthdate: " + Calendar.toString(e._1._2) + " - Result: " + tmp.get + "\n")
        }
      })
      text_area.appendText("\n")
      text_area.appendText("Prescriptions: \n")
      prescl1.foreach(p => {
        text_area.appendText("Date: " + Calendar.toString(p._3) + " - ")
        text_area.appendText("Patient: " + p._1._1 + " - ")
        text_area.appendText("Description: " + p._4 + "\n")
      })
    }

    if(FxApp.mf2.containsPersonDoctor(FxApp.user)) {
      text_area.appendText("Appointments: \n")
      pal2.foreach(a => {
        text_area.appendText("Date: " + Calendar.toString(a._2) + " - " + "Patient name: " + a._1._1 + " - " + " Birthdate: " + Calendar.toString(a._1._2) + "\n")
      })
      text_area.appendText("\n")
      text_area.appendText("Exams: \n")
      pexl2.foreach(e => {
        text_area.appendText("Date: " + Calendar.toString(e._4) + " - " + "Patient name: " + e._1._1 + " - " + " Birthdate: " + Calendar.toString(e._1._2) + "\n")
      })
      text_area.appendText("\n")
      text_area.appendText("Prescriptions: \n")
      prescl2.foreach(p => {
        text_area.appendText("Date: " + Calendar.toString(p._3) + " - ")
        text_area.appendText("Patient: " + p._1._1 + " - ")
        text_area.appendText("Description: " + p._4 + "\n")
      })
    }
  }

  def onLogoutClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("LoginController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    logout_button.getScene().setRoot(mainViewRoot)
  }

  def onHomeClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("HomeControllerDoctor.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    home_button.getScene().setRoot(mainViewRoot)
  }

  def onExamsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("ExamsControllerDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    exams_button.getScene().setRoot(mainViewRoot)
  }

  def onAppClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("AppointmentsControllerDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    appointments_button.getScene().setRoot(mainViewRoot)
  }

  def onHistoryClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("HistoryControllerDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    appointments_button.getScene().setRoot(mainViewRoot)
  }

  def onPrescriptionsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("PrescriptionsControllerDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    appointments_button.getScene().setRoot(mainViewRoot)
  }

  def onProfileClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("UserProfileDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    appointments_button.getScene().setRoot(mainViewRoot)
  }
}
