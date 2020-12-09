import java.net.URL
import java.util.ResourceBundle

import PersonList.{NIF, People, Person}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Label, TextArea}
import javafx.stage.{Modality, Stage}

class UserProfile extends Initializable{

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
  private var receipts_button: Label = _

  @FXML
  private var text_area: TextArea = _


  private val x1 = FxApp.mf1.docl
  private val x2 = FxApp.mf2.docl
  private val usr = FxApp.user


  override def initialize(location: URL, resources: ResourceBundle): Unit = {

    text_area.appendText("Welcome back, "+ usr._1 + "\n" + "\n")
    text_area.appendText("User Information"+ "\n")
    val bt = usr._2
    text_area.appendText("Birth Date: " + Calendar.toString(bt) + "\n")
    text_area.appendText("NIF: "+ usr._3 + "\n")
    val tmp = usr._4
    tmp match {
      case Some(tmp) => text_area.appendText("Phone Number: " + tmp + "\n" )
    }
    text_area.appendText("Password: "+ usr._7 + "\n")
    // GP (que é NIF)
    val tmp2 = usr._6
    tmp2 match {
      case None => text_area.appendText("No General Practitioner" + "\n")
      case Some(tmp2) => text_area.appendText("General Practitioner: " + x1.searchPersonNIF(tmp2).get._1 + ", " + x1.searchPersonNIF(tmp2).get._5.get + "\n" )
    }
  }

  def findGP(l1: PersonList, l2: PersonList, n: NIF): Person = {
    val doc1 = l1.searchPersonNIF(n)
    val doc2 = l2.searchPersonNIF(n)
    doc2 match {
      case Some(doc2) => doc2
    }
    doc1 match {
      case Some(doc1) => doc1
    }
  }

  def onPrescriptionsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("PrescriptionsController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    prescriptions_button.getScene().setRoot(mainViewRoot)
  }

  def onProfileClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("PrescriptionsController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    profile_button.getScene().setRoot(mainViewRoot)
  }

  def onHistoryClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("HistoryController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    history_button.getScene().setRoot(mainViewRoot)
  }

  def onEditPhoneNR: Unit = {
    val secondStage: Stage = new Stage()
    secondStage.initModality(Modality.APPLICATION_MODAL)
    secondStage.initOwner(edit_phone_button.getScene().getWindow)
    var fxmlLoader = new FXMLLoader(getClass.getResource("EditPhoneNumber.fxml"))
    var mainViewRoot: Parent = fxmlLoader.load()
    var scene = new Scene(mainViewRoot)
    secondStage.setScene(scene)
    secondStage.show()
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

  def onReceiptsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("ReceiptsController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    receipts_button.getScene().setRoot(mainViewRoot)
  }


}
