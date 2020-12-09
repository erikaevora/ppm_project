import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.Label
import javafx.scene.{Parent, Scene}
import javafx.stage.{Modality, Stage}

class AppointmentsControllerDoc extends Initializable{

  @FXML
  private var logout_button: Label = _

  @FXML
  private var home_button: Label = _

  @FXML
  private var exams_button: Label = _

  @FXML
  private var appointments_button: Label = _

  @FXML
  private var all_appointments_doc_button: Label = _

  @FXML
  private var all_appointments_today_doc_button: Label = _

  @FXML
  private var next_appointment_doc_button: Label = _


  @FXML
  private var delete_appointment_doc_button: Label = _


  override def initialize(location: URL, resources: ResourceBundle): Unit = {

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

  def onAllAppointmentsDocClicked: Unit = {
    val secondStage: Stage = new Stage()
    secondStage.initModality(Modality.APPLICATION_MODAL)
    secondStage.initOwner(all_appointments_doc_button.getScene().getWindow)
    val fxmlLoader = new FXMLLoader(getClass.getResource("AllAppointmentsDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val scene = new Scene(mainViewRoot)
    secondStage.setScene(scene)
    secondStage.show()
  }

  def onNextAppointmentDocClicked: Unit = {
    val secondStage: Stage = new Stage()
    secondStage.initModality(Modality.APPLICATION_MODAL)
    secondStage.initOwner(next_appointment_doc_button.getScene().getWindow)
    val fxmlLoader = new FXMLLoader(getClass.getResource("NextAppointmentDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val scene = new Scene(mainViewRoot)
    secondStage.setScene(scene)
    secondStage.show()
  }

  def onDeleteAppointmentDocClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("DeleteAppointmentDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    delete_appointment_doc_button.getScene().setRoot(mainViewRoot)
  }

  def onAllAppointmentsTodayDocClicked: Unit = {
    val secondStage: Stage = new Stage()
    secondStage.initModality(Modality.APPLICATION_MODAL)
    secondStage.initOwner(all_appointments_doc_button.getScene().getWindow)
    val fxmlLoader = new FXMLLoader(getClass.getResource("AllAppointmentsTodayDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val scene = new Scene(mainViewRoot)
    secondStage.setScene(scene)
    secondStage.show()
  }

}
