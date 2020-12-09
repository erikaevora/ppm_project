import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.Parent
import javafx.scene.control._

import scala.util.{Failure, Success, Try}

class PrescriptionsController extends Initializable {

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
  private var receipts_button: Label = _

  @FXML
  private var profile_button: Label = _

  @FXML
  private var text_area: TextArea = _

  private val x1 = FxApp.mf1.prescl.getPatientPrescription(FxApp.user)
  private val x2 = FxApp.mf2.prescl.getPatientPrescription(FxApp.user)

  override def initialize(location: URL, resources: ResourceBundle): Unit = {

    if(x1.nonEmpty) {
      val list = x1
      list.foreach(a => {
        text_area.appendText("Date: "+ Calendar.toString(a._3) + "\n")
        text_area.appendText("Practitioner: " + a._2._1 + "\n")
        text_area.appendText("Description: " + a._4 + "\n")
        val tmp = a._5
        tmp match {
          case Some(tmp) => text_area.appendText("Bill: " + tmp + " " )
            if (a._6) text_area.appendText("this bill is paid" + "\n")
            else text_area.appendText("this bill is not paid" + "\n")
          case None =>
        }
        text_area.appendText("Medical Facility: " + FxApp.mf1.nome + "\n \n")
      })

    }

    if(x2.nonEmpty) {
      val list = x2
      list.foreach(a => {
        text_area.appendText("Date: " + Calendar.toString(a._3) + "\n")
        text_area.appendText("Practitioner: " + a._2._1 + "\n")
        text_area.appendText("Description: " + a._4 + "\n")
        val tmp = a._5
        tmp match {
          case Some(tmp) => text_area.appendText("Bill: " + tmp + " ")
            if (a._6) text_area.appendText("this bill is paid" + "\n")
            else text_area.appendText("this bill is not paid" + "\n")
        }
        text_area.appendText("Medical Facility: " + FxApp.mf1.nome + "\n \n")
      })
    }
    if (!x1.nonEmpty && !x2.nonEmpty) text_area.appendText("No prescriptions available" + "\n")
  }

  def onPrescriptionsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("PrescriptionsController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    prescriptions_button.getScene().setRoot(mainViewRoot)
  }

  def onHistoryClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("HistoryController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    history_button.getScene().setRoot(mainViewRoot)
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

}