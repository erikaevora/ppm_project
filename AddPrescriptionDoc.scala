import java.net.URL
import java.util.ResourceBundle

import PersonList.Person
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.Parent
import javafx.scene.control._

class AddPrescriptionDoc extends Initializable {

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
  private var confirm_button: Button = _

  @FXML
  private var cancel_button: Button = _

  @FXML
  private var combo_box: ComboBox[String] = _

  @FXML
  private var warning_label: Label = _

  private var cli: Person = FxApp.user
  private var med: String = ""


  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    FxApp.mf1.patl.people.foreach(a => {
      combo_box.getItems().add(personToString(a))
    })
    FxApp.mf2.patl.people.foreach(a => {
      combo_box.getItems().add(personToString(a))
    })
  }

  def personToString(p: Person): String = {
    "Client: " + p._1 + " - NIF: " + p._3
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
    val str = combo_box.getValue
    val nif = str.split(" - ")(1).split(": ")(1)
    val tmp1 = FxApp.mf1.searchPersonNIFClient(nif)
    val tmp2 = FxApp.mf2.searchPersonNIFClient(nif)
    tmp1 match {
      case None => cli = cli
      case _ => cli = tmp1.get
    }
    tmp2 match {
      case None => cli = cli
      case _ => cli = tmp2.get
    }
    text_area.setEditable(true)
  }

  def onConfirmClicked: Unit = {
    med = text_area.getText()
    if(med.equals("")) warning_label.setVisible(true)
    else {
      val presc = (cli, FxApp.user, Calendar.getCurrentTime(), med, None, false)
      FxApp.mf1 = FxApp.mf1.addPrescription(presc)

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