import java.net.URL
import java.util.ResourceBundle

import PersonList.{NIF, Person}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.{Label, TextArea}
import javafx.scene.{Parent, Scene}
import javafx.stage.{Modality, Stage, WindowEvent}

class UserProfileDoc extends Initializable{

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
  private var edit_phone_button: Label = _

  @FXML
  private var profile_button: Label = _

  @FXML
  private var text_area: TextArea = _


  private val usr = FxApp.user

  override def initialize(location: URL, resources: ResourceBundle): Unit = {

    text_area.appendText("Welcome back, "+ usr._1 + "\n\n")
    text_area.appendText("User Information"+ "\n")
    val bt = usr._2
    text_area.appendText("Birth Date: " + Calendar.toString(bt) + "\n")
    text_area.appendText("NIF: "+ usr._3 + "\n")
    val tmp = usr._4
    tmp match {
      case Some(tmp) => text_area.appendText("Phone Number: " + tmp + "\n" )
    }
    text_area.appendText("Password: "+ usr._7 + "\n")
    text_area.appendText("Specialty: " + usr._5.get)
  }

  def onPrescriptionsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("PrescriptionsControllerDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    prescriptions_button.getScene().setRoot(mainViewRoot)
  }

  def onProfileClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("UserProfileDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    profile_button.getScene().setRoot(mainViewRoot)
  }

  def onHistoryClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("HistoryControllerDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    history_button.getScene().setRoot(mainViewRoot)
  }

  def onEditPhoneNR: Unit = {
    val secondStage: Stage = new Stage()
    secondStage.initModality(Modality.APPLICATION_MODAL)
    secondStage.initOwner(edit_phone_button.getScene().getWindow)
    val fxmlLoader = new FXMLLoader(getClass.getResource("EditPhoneNumber.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val scene = new Scene(mainViewRoot)
    secondStage.setScene(scene)
    secondStage.show()
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
}
