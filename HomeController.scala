import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Parent
import javafx.scene.control.{Button, Label}
import javafx.scene.paint.Color

class HomeController {
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
  private var receipts_button: Label = _

  @FXML
  private var history_button: Label = _

  @FXML
  private var profile_button: Label = _

  @FXML
  private var schedule_exam_button: Label = _

  @FXML
  private var schedule_app_button: Label = _


  def onExamHovered: Unit = {
    exams_button.setTextFill(Color.web("0X4C96C7"))
  }

  def onExamHoverExited: Unit = {
    exams_button.setTextFill(Color.BLACK)
  }

  def onAppHovered: Unit = {
    appointments_button.setTextFill(Color.web("0X4C96C7"))
  }

  def onAppHoverExited: Unit = {
    appointments_button.setTextFill(Color.BLACK)
  }

  def onPrescriptionHovered: Unit = {
    prescriptions_button.setTextFill(Color.web("0X4C96C7"))
  }

  def onPrescriptionHoverExited: Unit = {
    prescriptions_button.setTextFill(Color.BLACK)
  }

  def onReceiptsHovered: Unit = {
    receipts_button.setTextFill(Color.web("0X4C96C7"))
  }

  def onReceiptsHoverExited: Unit ={
    receipts_button.setTextFill(Color.BLACK)
  }

  def onHistoryHovered: Unit = {
    history_button.setTextFill(Color.web("0X4C96C7"))
  }

  def onHistoryHoverExited: Unit = {
    history_button.setTextFill(Color.BLACK)
  }

  def onProfileHovered: Unit = {
      profile_button.setTextFill(Color.web("0X4C96C7"))
  }

  def onProfileHoverExited: Unit = {
    profile_button.setTextFill(Color.BLACK)
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

  def onScheduleExamClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("ScheduleExamController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    schedule_exam_button.getScene().setRoot(mainViewRoot)
  }

  def onScheduleAppClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("ScheduleAppointmentController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    schedule_app_button.getScene().setRoot(mainViewRoot)
  }
}
