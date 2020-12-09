import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.ScrollPane.ScrollBarPolicy
import javafx.scene.control.{Button, Label, ScrollPane, TextArea}
import javafx.scene.image.ImageView
import javafx.stage.{Modality, Stage}

class ExamsController extends Initializable{

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

  @FXML
  private var schedule_exam_button: ImageView = _

  @FXML
  private var change_cancel_button: Button = _

  private val x1 = FxApp.mf1.patl.searchPerson(FxApp.user._3)
  private val x2 = FxApp.mf2.patl.searchPerson(FxApp.user._3)

  override def initialize(location: URL, resources: ResourceBundle): Unit = {

    if(x1.isDefined && FxApp.mf1.patl.people.contains(FxApp.user)) {
      val fel = FxApp.mf1.fexl
      fel.exams.foreach(e => {
        if(e._1 == x1.get) {
          text_area.appendText("Date: " + Calendar.toString(e._4) + "\n")
          text_area.appendText("Exam: " + e._3.toString + "\n")
          text_area.appendText("Practitioner: " + e._2._1 + "\n")
          text_area.appendText("Medical Facility: " + FxApp.mf1.nome + "\n \n")
        }
      })

    } else {
      val fel = FxApp.mf2.fexl
      fel.exams.foreach(e => {
        if(e._1 == x2.get) {
          text_area.appendText("Date: "+ Calendar.toString(e._4) + "\n")
          text_area.appendText("Exam: " + e._3.toString + "\n")
          text_area.appendText("Practitioner: " + e._2._1 + "\n")
          text_area.appendText("Medical Facility: " + FxApp.mf1.nome + "\n \n")
        }
      })
    }
  }

  def onScheduleClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("ScheduleExamController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    change_cancel_button.getScene().setRoot(mainViewRoot)
  }

  def onChangeCancelClicked: Unit = {
    val secondStage: Stage = new Stage()
    /*secondStage.setHeight(100)
    secondStage.setWidth(100)
    secondStage.centerOnScreen()*/
    secondStage.initModality(Modality.APPLICATION_MODAL)
    secondStage.initOwner(change_cancel_button.getScene().getWindow)
    val fxmlLoader = new FXMLLoader(getClass.getResource("ChangeExamCancelController.fxml"))
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

  def onHistoryClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("HistoryController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    history_button.getScene().setRoot(mainViewRoot)
  }

  def onReceiptsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("ReceiptsController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    receipts_button.getScene().setRoot(mainViewRoot)
  }
}
