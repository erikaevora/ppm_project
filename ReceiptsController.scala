import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.Parent
import javafx.scene.control.{Label, TextArea}
import javafx.scene.layout.VBox

class ReceiptsController extends Initializable {

  @FXML
  private var logout_button: Label = _

  @FXML
  private var home_button: Label = _

  @FXML
  private var exams_button: Label = _

  @FXML
  private var appointments_button: Label = _

  @FXML
  private var prescriptions_button: Label = _

  @FXML
  private var history_button: Label = _

  @FXML
  private var profile_button: Label = _

  @FXML
  private var receipts_button: Label = _

  @FXML
  private var text_area: TextArea = _

  private val x1 = FxApp.mf1.patl.searchPerson(FxApp.user._3)
  private val x2 = FxApp.mf2.patl.searchPerson(FxApp.user._3)

  override def initialize(location: URL, resources: ResourceBundle): Unit = { //receipts

    if (x1.nonEmpty) {
      val pal = FxApp.mf1.pal
      val pel = FxApp.mf1.pexl

      pal.appointments.foreach(a => {
        if (a._1 == x1.get) {
          text_area.appendText("Date: " + Calendar.toString(a._2) + "\n")
          text_area.appendText("Type of procedure: Appointment\n")
          text_area.appendText("Specialty: " + a._3._5.getOrElse("--") + "\n")
          text_area.appendText("Practitioner: " + a._3._1 + "\n")
          text_area.appendText("Cost: " + a._4.getOrElse(0) + "\n")
          text_area.appendText("Debt: " + a._5 + "\n \n")
        }

      })


      pel.exams.foreach(a => {
        if (a._1 == x1.get) {
          text_area.appendText("Date: " + Calendar.toString(a._4) + "\n")
          text_area.appendText("Type of procedure: Exam\n")
          text_area.appendText("Specialty: " + a._2._5.getOrElse("--") + "\n")
          text_area.appendText("Practitioner: " + a._2._1 + "\n")
          text_area.appendText("Cost: " + a._5.getOrElse(0) + "\n")
          text_area.appendText("Debt: " + a._7 + "\n \n")
        }

      })


    } else {
      val pal = FxApp.mf2.pal
      val pel = FxApp.mf2.pexl

      pal.appointments.foreach(a => {
        if (a._1 == x2.get) {
          text_area.appendText("Date: " + Calendar.toString(a._2) + "\n")
          text_area.appendText("Type of procedure: Appointment\n")
          text_area.appendText("Specialty: " + a._3._5.getOrElse("--") + "\n")
          text_area.appendText("Practitioner: " + a._3._1 + "\n")
          text_area.appendText("Cost: " + a._4.getOrElse(0) + "\n")
          text_area.appendText("Debt: " + a._5 + "\n \n")
        }

      })

      pel.exams.foreach(a => {
        if (a._1 == x2.get) { //??
          text_area.appendText("Date: " + Calendar.toString(a._4) + "\n")
          text_area.appendText("Type of procedure: Exam\n")
          text_area.appendText("Specialty: " + a._2._5.getOrElse("--") + "\n")
          text_area.appendText("Practitioner: " + a._2._1 + "\n")
          text_area.appendText("Cost: " + a._5.getOrElse(0) + "\n")
          text_area.appendText("Debt: " + a._7 + "\n \n")
        }
      })
    }

  }

  def onLogoutClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("LoginController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    logout_button.getScene().setRoot(mainViewRoot)
  }

  def onHomeClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("HomeController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    home_button.getScene().setRoot(mainViewRoot)
  }

  def onExamsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("ExamsController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    exams_button.getScene().setRoot(mainViewRoot)
  }

  def onAppClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("AppointmentsController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    appointments_button.getScene().setRoot(mainViewRoot)
  }

  def onPrescriptionsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("PrescriptionsController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    prescriptions_button.getScene().setRoot(mainViewRoot)
  }

  def onProfileClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("UserProfile.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    profile_button.getScene().setRoot(mainViewRoot)
  }
  def onReceiptsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("ReceiptsController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    receipts_button.getScene().setRoot(mainViewRoot)
  }


  def onHistoryClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("HistoryController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    history_button.getScene().setRoot(mainViewRoot)
  }

}