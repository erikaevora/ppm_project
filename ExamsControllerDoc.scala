import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Label, TextArea}
import javafx.stage.{Modality, Stage}

class ExamsControllerDoc extends Initializable{

  @FXML
  private var logout_button: Label = _

  @FXML
  private var home_button: Label = _

  @FXML
  private var exams_button: Label = _

  @FXML
  private var appointments_button: Label = _

  @FXML
  private var all_exams_doc_button: Label = _

  @FXML
  private var all_exams_today_doc_button: Label = _

  @FXML
  private var next_exam_doc_button: Label = _

  @FXML
  private var schedule_exam_doc_button: Label = _

  @FXML
  private var delete_exam_doc_button: Label = _

  @FXML
  private var edit_exam_doc_button: Label = _


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

  def onAllExamsDocClicked: Unit = {
    val secondStage: Stage = new Stage()
    secondStage.initModality(Modality.APPLICATION_MODAL)
    secondStage.initOwner(all_exams_doc_button.getScene().getWindow)
    val fxmlLoader = new FXMLLoader(getClass.getResource("AllExamsDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val scene = new Scene(mainViewRoot)
    secondStage.setScene(scene)
    secondStage.show()
  }

  def onAllExamsTodayDocClicked: Unit = {
    val secondStage: Stage = new Stage()
    secondStage.initModality(Modality.APPLICATION_MODAL)
    secondStage.initOwner(all_exams_today_doc_button.getScene().getWindow)
    val fxmlLoader = new FXMLLoader(getClass.getResource("AllExamsTodayDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val scene = new Scene(mainViewRoot)
    secondStage.setScene(scene)
    secondStage.show()
  }

  def onNextExamDocClicked: Unit = {
    val secondStage: Stage = new Stage()
    secondStage.initModality(Modality.APPLICATION_MODAL)
    secondStage.initOwner(next_exam_doc_button.getScene().getWindow)
    val fxmlLoader = new FXMLLoader(getClass.getResource("NextExamDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val scene = new Scene(mainViewRoot)
    secondStage.setScene(scene)
    secondStage.show()
  }

  def onEditExamClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("EditExamDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    edit_exam_doc_button.getScene().setRoot(mainViewRoot)
  }

  def onDeleteExamDocClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("DeleteExamDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    delete_exam_doc_button.getScene().setRoot(mainViewRoot)
  }

}
