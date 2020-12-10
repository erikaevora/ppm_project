import java.net.URL
import java.util.ResourceBundle

import AppointmentList.Appointment
import ExamList.{DateTime, Exam}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.Parent
import javafx.scene.control.{Button, CheckBox, ComboBox, Label}

class DeleteAppointmentDoc extends Initializable {

  @FXML
  private var logout_button: Label = _

  @FXML
  private var home_button: Label = _

  @FXML
  private var exams_button: Label = _

  @FXML
  private var appointments_button: Label = _

  @FXML
  private var appointments_combo: ComboBox[String] = _

  @FXML
  private var confirm_delete_appointment_checkbox: CheckBox = _

  @FXML
  private var confirm_appointment_delete_button: Button = _

  @FXML
  private var cancel_button: Button = _

  private var ex: Appointment = (FxApp.user, Calendar.getCurrentTime(), FxApp.user, None, false)

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    val aps1 = FxApp.mf1.wtl.filterAppointmentsDoc(FxApp.user).sortWith((a, b) => Calendar.isFirst(a._2, b._2))
    val aps2 = FxApp.mf2.wtl.filterAppointmentsDoc(FxApp.user).sortWith((a, b) => Calendar.isFirst(a._2, b._2))
    if(aps1.nonEmpty) {
      aps1.foreach(a => {
        appointments_combo.getItems().add(appointmentToString(a))
      })
    }
    if(aps2.nonEmpty) {
      aps2.foreach(a => {
        appointments_combo.getItems().add(appointmentToString(a))
      })
    }
  }

  def appointmentToString(ap: Appointment): String = {
    "Date: " + Calendar.toString(ap._2) + " - Name: " + ap._1._1 + " - Practitioner: " + ap._3._1
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
  def onComboAppointmentAction: Unit = {
    val str = appointments_combo.getValue
    val prts = getParts(str)
    val cli = prts._1
    val doc = prts._2
    val dt = stringToCal(prts._3)

    val tmp = FxApp.mf1.findAppointmentCombo(cli, doc, dt)
    tmp match {
      case None => ex
      case _ => ex = tmp.get
    }
    confirm_delete_appointment_checkbox.setVisible(true)
  }

  def getParts(str: String): (String, String, String) = {
    val d = str.split(" - ")(0).split(": ")(1)
    val cli = str.split(" - ")(1).split(": ")(1)
    val doc = str.split(" - ")(2).split(": ")(1)
    (cli, doc, d)
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

  def onCancelClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("AppointmentsControllerDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    cancel_button.getScene().setRoot(mainViewRoot)
  }

  def onCheckboxDeleteAppointmentClicked: Unit = {
    if (confirm_delete_appointment_checkbox.isSelected) confirm_appointment_delete_button.setDisable(false)
  }

  def onConfirmAppointmentDeleteClicked: Unit = {
    if(FxApp.mf1.containsAppointmentWL(ex)) FxApp.mf1 = FxApp.mf1.deleteAppointmentWL(ex).get
    if(FxApp.mf2.containsAppointmentWL(ex)) FxApp.mf2 = FxApp.mf2.deleteAppointmentWL(ex).get

    val fxmlLoader = new FXMLLoader(getClass.getResource("AppointmentsControllerDoc.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    confirm_appointment_delete_button.getScene().setRoot(mainViewRoot)
  }

}
