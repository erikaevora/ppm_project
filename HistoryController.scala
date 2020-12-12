import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.Parent
import javafx.scene.control.{Label, ScrollPane, TextArea}
import javafx.scene.paint.Color

class HistoryController extends Initializable {

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

  private val x1 = FxApp.mf1.patl.searchPerson(FxApp.user._3)
  private val x2 = FxApp.mf2.patl.searchPerson(FxApp.user._3)

  override def initialize(location: URL, resources: ResourceBundle): Unit = {

    if (x1.isDefined && FxApp.mf1.patl.people.contains(FxApp.user)) {
      val pal = FxApp.mf1.pal
      val pexl = FxApp.mf1.pexl
      val prescl = FxApp.mf1.prescl
      pal.appointments.foreach(a => {
        if (a._1._3 == x1.get._3) {
          text_area.appendText("Appointment: " + "\n")
          text_area.appendText("Date: "+ Calendar.toString(a._2) + "\n")
          text_area.appendText("Appointment: " + a._3._5.get.toString + "\n")
          text_area.appendText("Practitioner: " + a._3._1 + "\n")
          text_area.appendText("Medical Facility: " + FxApp.mf1.nome + "\n \n")
        }
      })
      pexl.exams.foreach(e => {
        if (e._1._3 == x1.get._3) {
          text_area.appendText("Exam: " + "\n")
          text_area.appendText("Date: "+ Calendar.toString(e._4) + "\n")
          text_area.appendText("Exam: " + e._3.toString + "\n")
          text_area.appendText("Practitioner: " + e._2._1 + "\n")
          text_area.appendText("Medical Facility: " + FxApp.mf1.nome + "\n \n")
        }
      })
      prescl.prescriptions.foreach(p => {
        if (p._1._3 == x1.get._3) {
          text_area.appendText("Prescription: " + "\n")
          text_area.appendText("Date: " + Calendar.toString(p._3) + "\n")
          text_area.appendText("Practitioner: " + p._2._1 + "\n")
          val tmp = p._5
          tmp match {
            case None => text_area.appendText("Bill: " + "no cost associated \n")
            case _ => text_area.appendText("Bill: " + p._5.get + "\n")
          }
          text_area.appendText("Description: " + p._4 + "\n")
          text_area.appendText("Medical Facility: " + FxApp.mf1.nome + "\n \n")
        }
      })

    } else {
      val pal = FxApp.mf2.pal
      val pexl = FxApp.mf2.pexl
      val prescl = FxApp.mf2.prescl
      pal.appointments.foreach(a => {
        if (a._1._3 == x2.get._3) {
          text_area.appendText("Appointment: " + "\n")
          text_area.appendText("Date: "+ Calendar.toString(a._2) + "\n")
          text_area.appendText("Appointment: " + a._3._5.get.toString + "\n")
          text_area.appendText("Practitioner: " + a._3._1 + "\n")
          text_area.appendText("Medical Facility: " + FxApp.mf1.nome + "\n \n")
        }
      })
      pexl.exams.foreach(e => {
        if (e._1._3 == x2.get._3) {
          text_area.appendText("Exam: " + "\n")
          text_area.appendText("Date: "+ Calendar.toString(e._4) + "\n")
          text_area.appendText("Exam: " + e._3.toString + "\n")
          text_area.appendText("Practitioner: " + e._2._1 + "\n")
          text_area.appendText("Medical Facility: " + FxApp.mf1.nome + "\n \n")
        }
      })
      prescl.prescriptions.foreach(p => {
        if (p._1._3 == x2.get._3) {
          text_area.appendText("Prescription: " + "\n")
          text_area.appendText("Date: " + Calendar.toString(p._3) + "\n")
          text_area.appendText("Practitioner: " + p._2._1 + "\n")
          text_area.appendText("Bill: " + p._5.get + "\n")
          text_area.appendText("Description: " + p._4 + "\n")
          text_area.appendText("Medical Facility: " + FxApp.mf1.nome + "\n \n")
        }
      })
    }

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

  def onHomeEntered: Unit = {
    home_button.setTextFill(Color.web("0X4C96C7"))
  }

  def onHomeExited: Unit = {
    home_button.setTextFill(Color.BLACK)
  }

  def onExamsEntered: Unit = {
    exams_button.setTextFill(Color.web("0X4C96C7"))
  }

  def onExamsExited: Unit = {
    exams_button.setTextFill(Color.BLACK)
  }

  def onAppEntered: Unit = {
    appointments_button.setTextFill(Color.web("0X4C96C7"))
  }

  def onAppExited: Unit = {
    appointments_button.setTextFill(Color.BLACK)
  }

  def onPrescEntered: Unit = {
    prescriptions_button.setTextFill(Color.web("0X4C96C7"))
  }

  def onPrescExited: Unit = {
    prescriptions_button.setTextFill(Color.BLACK)
  }

  def onReceiptsEntered: Unit = {
    receipts_button.setTextFill(Color.web("0X4C96C7"))
  }

  def onReceiptsExited: Unit = {
    receipts_button.setTextFill(Color.BLACK)
  }

  def onHistoryEntered: Unit = {
    history_button.setTextFill(Color.web("0X4C96C7"))
  }

  def onHistoryExited: Unit = {
    history_button.setTextFill(Color.BLACK)
  }

  def onProfileEntered: Unit = {
    profile_button.setTextFill(Color.web("0X4C96C7"))
  }

  def onProfileExited: Unit = {
    profile_button.setTextFill(Color.BLACK)
  }

}
