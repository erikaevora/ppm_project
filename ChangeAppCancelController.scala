import java.net.URL
import java.util.ResourceBundle

import AppointmentList.Appointment
import Calendar.toString
import PersonList.Person
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.Parent
import javafx.scene.control.{Button, ChoiceBox, ComboBox, DatePicker, Label, TextField}

import scala.util.{Failure, Success, Try}

class ChangeAppCancelController extends Initializable{

  @FXML
  private var app_date_box: ComboBox[Appointment] = _

  @FXML
  private var new_date_picker: DatePicker = _

  @FXML
  private var hour_text: TextField = _

  @FXML
  private var minute_box: ChoiceBox[Int] = _

  @FXML
  private var change_button: Button = _

  @FXML
  private var cancel_button: Button = _

  @FXML
  private var back_button: Button = _

  @FXML
  private var error_label: Label = _

  @FXML
  private var success_label: Label = _

  @FXML
  private var valid_date_label: Label = _

  private var date: Array[String] = Array("0", "0", "0")
  private var min: MinuteENUM.Value = MinuteENUM.Zero
  private val x1 = FxApp.mf1.patl.searchPerson(FxApp.user._3)
  private val x2 = FxApp.mf2.patl.searchPerson(FxApp.user._3)

  override def initialize(location: URL, resources: ResourceBundle): Unit = {

    minute_box.getItems.addAll(0, 30)

    if(x1.isDefined && FxApp.mf1.patl.people.contains(FxApp.user)) {
      val wtl = FxApp.mf1.wtl
      wtl.appointments.foreach(a => {
        if(a._1 == x1.get) {
          app_date_box.getItems.add(a /*Calendar.toString(e._4)*/)
        }
      })

    } else {
      val wtl = FxApp.mf2.wtl
      wtl.appointments.foreach(a => {
        if(a._1 == x2.get) {
          app_date_box.getItems.add(a /*Calendar.toString(e._4)*/)
        }
      })
    }
  }

  def onDatePicked: Unit = {
    date = new_date_picker.getValue.toString.split("-")  //year-month-day
    val verData = Calendar.setDateTime(date(2).toInt, date(1).toInt, date(0).toInt, 0, min).get
    if (Calendar.isFirst(verData, FxApp.today)) valid_date_label.setVisible(true) else valid_date_label.setVisible(false)
  }

  def onChangeClicked: Unit = {
    error_label.setVisible(false)
    success_label.setVisible(false)

    val hour = hour_text.getText
    val minute = minute_box.getValue
    val a = app_date_box.getValue

    if (a != null && new_date_picker != null && hour.length >0 && minute_box != null) {
      if (minute == 0) min = MinuteENUM.Zero else min = MinuteENUM.Trinta
      if (new_date_picker.getValue == null) {
        valid_date_label.setVisible(true)
        return
      }
      if (hour_text.getText.toInt < 0 || hour_text.getText.toInt > 23) {
        error_label.setText("The date and time you chose are unavailable.")
        error_label.setVisible(true)
        error_label.setMinWidth(250)
        return
      }
      val dateHour = Calendar.setDateTime(date(2).toInt, date(1).toInt, date(0).toInt, hour.toInt, min).get
      if (Calendar.isFirst(dateHour, FxApp.today)) {
        valid_date_label.setVisible(true)
        return
      }
      if (x1.isDefined && FxApp.mf1.patl.people.contains(FxApp.user)) {

        if (!FxApp.mf1.wtl.availableSlot(dateHour, a._3)) {
          error_label.setText("The date and time you chose are unavailable.")
          error_label.setVisible(true)
          error_label.setMinWidth(250)
        }
        else {
          val new_mf = FxApp.mf1.changeAppointmentDateWL(a, dateHour)
          new_mf match {
            case None => {
              error_label.setText("Something went wrong.")
              error_label.setVisible(true)
            }
            case _ => {
              FxApp.mf1 = new_mf.get
              success_label.setText("The date of your appointment was successfully changed.")
              success_label.setVisible(true)
              valid_date_label.setVisible(false)
            }
          }
        }

      } else {

        if (!FxApp.mf2.wtl.availableSlot(dateHour, a._3)) {
          error_label.setText("The date and time you chose are unavailable.")
          error_label.setVisible(true)
          error_label.setMinWidth(250)
        }
        else {
          val new_mf = FxApp.mf2.changeAppointmentDateWL(a, dateHour)
          new_mf match {
            case None => {
              error_label.setText("Something went wrong.")
              error_label.setVisible(true)
            }
            case _ => {
              FxApp.mf2 = new_mf.get
              success_label.setText("The date of your appointment was successfully changed.")
              success_label.setVisible(true)
            }
          }
        }
      }
    }else {
      error_label.setText("Please complete all the fields above.")
      error_label.setVisible(true)
    }

  }

  def onCancelClicked: Unit = {
    error_label.setVisible(false)
    success_label.setVisible(false)

    val a = app_date_box.getValue

    if (a != null) {
      if (x1.isDefined && FxApp.mf1.patl.people.contains(FxApp.user)) {
        val new_mf = FxApp.mf1.deleteAppointmentWL(a)
        FxApp.mf1 = new_mf.get
        success_label.setText("Your appointment was successfully canceled.")
        success_label.setVisible(true)

      } else {
        val new_mf = FxApp.mf2.deleteAppointmentWL(a)
        FxApp.mf2 = new_mf.get
        success_label.setText("Your appointment was successfully canceled.")
        success_label.setVisible(true)
      }
    } else {
      error_label.setText("Please select an appointment to cancel.")
      error_label.setVisible(true)
    }

  }

  def onGoBackClicked: Unit = {
    back_button.getScene().getWindow.hide()
  }
}