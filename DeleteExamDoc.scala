import java.net.URL
import java.util.ResourceBundle

import ExamList.{Exam, Exams}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.Parent
import javafx.scene.control.{Button, CheckBox, ComboBox, Label, TextField}

class DeleteExamDoc extends Initializable {

  @FXML
  private var logout_button: Label = _

  @FXML
  private var home_button: Label = _

  @FXML
  private var exams_button: Label = _

  @FXML
  private var appointments_button: Label = _

  @FXML
  private var exams_combo: ComboBox[String] = _

  @FXML
  private var confirm_delete_exam_checkbox: CheckBox = _

  @FXML
  private var confirm_exam_delete_button: Button = _

  @FXML
  private var cancel_button: Button = _

  private var ex: Exam = (FxApp.user, FxApp.user, SpecialtyENUM.Dermatology, Calendar.getCurrentTime(), Some(0), None, false)

  private val x1 = FxApp.mf1.fexl.filterExamDoc(FxApp.user)
  private val x2 = FxApp.mf2.fexl.filterExamDoc(FxApp.user)

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    x1.foreach(a => {
      exams_combo.getItems().add(examToString(a))
    })
    x2.foreach(a => {
      exams_combo.getItems().add(examToString(a))
    })
  }

  def examToString(ex: Exam): String = {
    "Date: " + Calendar.toString(ex._4) + " - Name: " + ex._1._1 + " - Practitioner: " + ex._2._1
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

  //select an item from combobox
  def onComboAction: Unit = {
    val str = exams_combo.getValue
    val arg = getParts(str)
    val dt = stringToCal(arg._1)
    val doc = arg._2
    val cli = arg._3

    val tmp = FxApp.mf1.findExamFEL(doc, cli, dt)
    tmp match {
      case None => ex = ex
      case _ => ex = tmp.get
    }

    confirm_exam_delete_button.setDisable(false)
  }

  def stringToCal(str: String): Calendar = {
    val date = str.split(" at ")(0)
    val time = str.split(" at ")(1)
    val day = date.split("-")(0)
    val month = date.split("-")(1)
    val year = date.split("-")(2)
    val hour = time.split("h")(0)
    val min = time.split("h")(1)
    val minute = if(min.equals("0")) MinuteENUM.Zero else MinuteENUM.Trinta

    Calendar.setDateTime(day.toInt, month.toInt, year.toInt, hour.toInt, minute).get
  }

  def getParts(str: String): (String, String, String) = {
    val str0 = str.split(" - ")(0).split(": ")(1)
    val str1 = str.split(" - ")(1).split(": ")(1)
    val str2 = str.split(" - ")(2).split(": ")(1)
    (str0, str1, str2)
  }

  def onCancelClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("ExamsControllerDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    cancel_button.getScene().setRoot(mainViewRoot)
  }

  def onCheckboxDeleteClicked: Unit = {
    if (confirm_delete_exam_checkbox.isSelected) confirm_exam_delete_button.setDisable(false)
  }

  def onConfirmExamDeleteClicked: Unit = {
    if (FxApp.mf1.containsExamF(ex)) FxApp.mf1 = FxApp.mf1.deleteExamF(ex)
    if (FxApp.mf2.containsExamF(ex)) FxApp.mf2 = FxApp.mf2.deleteExamF(ex)

    val fxmlLoader = new FXMLLoader(getClass.getResource("ExamsControllerDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    confirm_exam_delete_button.getScene().setRoot(mainViewRoot)
  }

}
