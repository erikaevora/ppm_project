import java.net.URL
import java.util.ResourceBundle

import Calendar.toString
import ExamList.Exam
import PersonList.Person
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.control.{Button, ChoiceBox, ComboBox, DatePicker, Label, TextField}

import scala.collection.mutable
import scala.util.{Failure, Success, Try}

class ChangeExamCancelController extends Initializable{

//  @FXML
//  private var exam_date_box: ComboBox[Exam] = _

  @FXML
  private var exam_date_box: ComboBox[String] = _

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
  private var date_warning: Label = _

  private var date: Array[String] = Array("0", "0", "0")
  private var min: MinuteENUM.Value = MinuteENUM.Zero
  private val x1 = FxApp.mf1.patl.searchPerson(FxApp.user._3)
  private val x2 = FxApp.mf2.patl.searchPerson(FxApp.user._3)

  var mapa = new mutable.HashMap[String, Exam]()


  override def initialize(location: URL, resources: ResourceBundle): Unit = {

    minute_box.getItems.addAll(0, 30)

    if(x1.isDefined && FxApp.mf1.patl.people.contains(FxApp.user)) {
      val fel = FxApp.mf1.fexl
      fel.exams.foreach(e => {
        if(e._1._3 == x1.get._3) {
          mapa.addOne(examToString(e), e)
          exam_date_box.getItems.add(examToString(e) /*Calendar.toString(e._4)*/)
        }
      })

    } else {
      val fel = FxApp.mf2.fexl
      fel.exams.foreach(e => {
        if(e._1._3 == x2.get._3) {
          mapa.addOne(examToString(e), e)
          exam_date_box.getItems.add(examToString(e) /*Calendar.toString(e._4)*/)
        }
      })
    }
  }

  def examToString(ex: Exam): String = {
    "Date: " + Calendar.toString(ex._4) + " - Specialty: " + ex._2._5.get + " - Practitioner: " + ex._2._1
  }

  def onDatePicked: Unit = {
    date = new_date_picker.getValue.toString.split("-")  //year-month-day
    val verData = Calendar.setDateTime(date(2).toInt, date(1).toInt, date(0).toInt, 0, min).get
    if (Calendar.isFirst(verData, FxApp.today)) date_warning.setVisible(true) else date_warning.setVisible(false)
  }

  def onChangeClicked: Unit = {
    error_label.setVisible(false)
    success_label.setVisible(false)

    val hour = hour_text.getText
    val minute = minute_box.getValue
    val e = exam_date_box.getValue

    if (e != null && new_date_picker != null && hour.length >0 && minute_box != null && new_date_picker.getValue != null) {
      if (minute == 0) min = MinuteENUM.Zero else min = MinuteENUM.Trinta
      if (hour_text.getText.toInt < 0 || hour_text.getText.toInt > 23) {
        error_label.setText("The date and time you chose are unavailable.")
        error_label.setVisible(true)
        return
      }
      val dateHour = Calendar.setDateTime(date(2).toInt, date(1).toInt, date(0).toInt, hour.toInt, min).get

      if (x1.isDefined && FxApp.mf1.patl.people.contains(FxApp.user)) {

        if (!Calendar.isFirst(dateHour, FxApp.today)) {

          if (!FxApp.mf1.fexl.checkDateAvailability(dateHour)) {
            error_label.setText("The date and time you chose are unavailable.")
            error_label.setVisible(true)
          }
          else {
            val new_mf = FxApp.mf1.changeExamDate(mapa.get(e).get, dateHour)
            new_mf match {
              case None => {
                error_label.setText("Select a Valid Date")
                error_label.setVisible(true)
              }
              case _ => {
                FxApp.mf1 = new_mf.get
                success_label.setText("The date of your exam was successfully changed.")
                success_label.setVisible(true)
              }
            }
          }

        } else {

          if (!FxApp.mf2.fexl.checkDateAvailability(dateHour)) {
            error_label.setText("The date and time you chose are unavailable.")
            error_label.setVisible(true)
          }
          else {
            val new_mf = FxApp.mf2.changeExamDate(mapa.get(e).get, dateHour)
            new_mf match {
              case None => {
                error_label.setText("Something went wrong.")
                error_label.setVisible(true)
              }
              case _ => {
                FxApp.mf2 = new_mf.get
                success_label.setText("The date of your exam was successfully changed.")
                success_label.setVisible(true)
              }
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

    val e = exam_date_box.getValue

    if (e != null) {
      if (x1.isDefined && FxApp.mf1.patl.people.contains(FxApp.user)) {
        val new_mf = FxApp.mf1.deleteExamF(mapa.get(e).get)
        FxApp.mf1 = new_mf
        success_label.setText("Your exam was successfully canceled.")
        success_label.setAlignment(Pos.CENTER)
        success_label.setVisible(true)

      } else {
        val new_mf = FxApp.mf2.deleteExamF(mapa.get(e).get)
        FxApp.mf2 = new_mf
        success_label.setText("Your exam was successfully canceled.")
        success_label.setVisible(true)
      }
    } else {
      error_label.setText("Please select an exam to cancel.")
      error_label.setVisible(true)
    }

  }

  def onGoBackClicked: Unit = {
    back_button.getScene().getWindow.hide()
  }
}
