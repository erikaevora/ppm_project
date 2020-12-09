import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.Parent
import javafx.scene.control._

class PrescriptionsControllerDoc extends Initializable {

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
  private var text_area: TextArea = _

  @FXML
  private var add_button: Button = _

  @FXML
  private var delete_button: Button = _

  @FXML
  private var alter_button: Button = _

  private val x1 = FxApp.mf1
  private val x2 = FxApp.mf2

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    val list = x1.getDoctorPrescription(FxApp.user)
    val list2 = x2.getDoctorPrescription(FxApp.user)

    if(list.length > 0) {
      list.foreach(a => {
        text_area.appendText("Date: " + Calendar.toString(a._3) + " - ")
        text_area.appendText("Patient: " + a._1._1 + " - ")
        text_area.appendText("Description: " + a._4 + "\n\n")
      })
    }
    if(list2.length > 0) {
      list2.foreach(a => {
        text_area.appendText("Date: " + Calendar.toString(a._3) + " - ")
        text_area.appendText("Patient: " + a._1._1 + " - ")
        text_area.appendText("Description: " + a._4 + "\n\n")
      })
    }
    if (!list.nonEmpty) text_area.setText("No prescriptions yet...")
  }

  def onPrescriptionsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("PrescriptionsControllerDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    prescriptions_button.getScene().setRoot(mainViewRoot)
  }

  def onHistoryClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("HistoryControllerDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    history_button.getScene().setRoot(mainViewRoot)
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

  def onProfileClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("UserProfileDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    profile_button.getScene().setRoot(mainViewRoot)
  }

  def onAddClick: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("AddPrescriptionDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    add_button.getScene().setRoot(mainViewRoot)
  }

  def onDeleteClick: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("DeletePrescriptionDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    delete_button.getScene().setRoot(mainViewRoot)
  }

  def onAlterClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("AlterPrescriptionDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    alter_button.getScene().setRoot(mainViewRoot)
  }

}