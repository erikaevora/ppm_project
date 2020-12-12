import java.net.URL
import java.util.ResourceBundle

import PersonList.Person
import PrescriptionList.{Description, Prescription}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.Parent
import javafx.scene.control._

import scala.collection.mutable

class AlterPrescriptionDoc extends Initializable {

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
  private var confirm_button: Button = _

  @FXML
  private var cancel_button: Button = _

//  @FXML
//  private var combo_box: ComboBox[Prescription] = _

  @FXML
  private var combo_box: ComboBox[String] = _

  @FXML
  private var check_box: CheckBox = _

  @FXML
  private var text_area: TextArea = _

  @FXML
  private var warning_label: Label = _

  private var cli: Person = FxApp.user
  private var presc: Prescription = (cli, FxApp.user, Calendar.getCurrentTime(), "", None, false)
  private var desc: Description = ""

  var mapa = new mutable.HashMap[String, Prescription]()

  def examToString(prs: Prescription): String = {
    "Client: " + prs._1._1 + " - DateTime: " + Calendar.toString(prs._3) + " - Description: " + prs._4
  }

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    FxApp.mf1.getDoctorPrescription(FxApp.user).foreach(a => {
      mapa.addOne(examToString(a), a)
      combo_box.getItems().add(examToString(a))
    })
    FxApp.mf2.getDoctorPrescription(FxApp.user).foreach(a => {
      mapa.addOne(examToString(a), a)
      combo_box.getItems().add(examToString(a))
    })
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

  def onComboAction: Unit = {
    presc = mapa.get(combo_box.getValue).get
    text_area.setEditable(true)
    check_box.setVisible(true)
  }

  def onCheckSelected: Unit = {
    if (check_box.isSelected) confirm_button.setDisable(false)
  }

  def onConfirmClicked: Unit = {
    desc = text_area.getText()
    if(desc == "") warning_label.setVisible(true)
    else {
      val tmp1 = FxApp.mf1.alterPrescription(presc, presc._5, desc)
      tmp1 match {
        case None =>
        case Some(tmp1) => FxApp.mf1 = tmp1
      }
      val tmp2 = FxApp.mf2.alterPrescription(presc, presc._5, desc)
      tmp2 match {
        case None =>
        case Some(tmp2) => FxApp.mf2 = tmp2
      }

      val fxmlLoader = new FXMLLoader(getClass.getResource("PrescriptionsControllerDoc.fxml"))
      val mainViewRoot: Parent = fxmlLoader.load()
      confirm_button.getScene().setRoot(mainViewRoot)
    }
  }

  def onCancelClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("PrescriptionsControllerDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    cancel_button.getScene().setRoot(mainViewRoot)
  }

}