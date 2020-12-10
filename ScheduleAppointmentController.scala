import java.net.URL
import java.util.ResourceBundle

import PersonList.Person
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.Parent
import javafx.scene.control.{Button, ChoiceBox, ComboBox, DatePicker, Label, TextField}
import javafx.scene.layout.Region

class ScheduleAppointmentController extends Initializable{

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
  private var specialty_box: ComboBox[String] = _

  @FXML
  private var practitioner_box: ComboBox[String] = _

  @FXML
  private var date_picker: DatePicker = _

  @FXML
  private var date_label: Label = _

  @FXML
  private var hour_text: TextField = _

  @FXML
  private var minute_picker: ChoiceBox[Int] = _

  @FXML
  private var submit_button: Button = _

  @FXML
  private var validate_date_label: Label = _

  @FXML
  private var error_label: Label = _

  @FXML
  private var insert_valid_date: Label = _

  private var date: Array[String] = Array("0", "0", "0")
  private var spec: SpecialtyENUM.Value = SpecialtyENUM.GP
  private var pract: Person = ("", Calendar(0,0,0,0,MinuteENUM.Zero), "", Some(""), Some(SpecialtyENUM.GP), None, "", false)
  private var min: MinuteENUM.Value = MinuteENUM.Zero
  private val x1 = FxApp.mf1.patl.searchPerson(FxApp.user._3)


  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    //val x2 = FxApp.mf2.patl.searchPerson(FxApp.user._3)

    if(x1.isDefined && FxApp.mf1.patl.people.contains(FxApp.user)) {
      val specList = FxApp.mf1.getSpecialties()
      specList.foreach(s => specialty_box.getItems.add(s.toString))
    } else {
      val specList = FxApp.mf2.getSpecialties()
      specList.foreach(s => specialty_box.getItems.add(s.toString))
    }

    minute_picker.getItems.addAll(0, 30)
  }

  def onLogoutClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("LoginController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    logout_button.getScene().setRoot(mainViewRoot)
  }

  def onHomeClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("HomeController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    home_button.getScene.setRoot(mainViewRoot)
  }

  def onExamsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("ExamsController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    exams_button.getScene.setRoot(mainViewRoot)
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

  def onReceiptsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("ReceiptsController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    receipts_button.getScene().setRoot(mainViewRoot)
  }

  def onHistoryClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("HistoryController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    history_button.getScene().setRoot(mainViewRoot)
  }

  def onSpecialtyPicked: Unit = {
    val specialty = specialty_box.getValue
    practitioner_box.getItems.removeAll()

    SpecialtyENUM.values.foreach(x => if(x.toString == specialty) spec = x)

    if(x1.isDefined && FxApp.mf1.patl.people.contains(FxApp.user)) {
      val practList = FxApp.mf1.docl.people.filter(x => x._5.get.toString == specialty)
      practList.foreach (p => practitioner_box.getItems.add (p._1))
    } else {
      val practList = FxApp.mf2.docl.people.filter(x => x._5.get.toString == specialty)
      practList.foreach (p => practitioner_box.getItems.add (p._1))
    }
  }

  def onPractitionerPicked: Unit = {
    val practitioner = practitioner_box.getValue
    if(x1.isDefined && FxApp.mf1.patl.people.contains(FxApp.user)) {
      pract = FxApp.mf1.docl.people.filter(x => x._1 == practitioner).head
    } else {
      pract = FxApp.mf2.docl.people.filter(x => x._1 == practitioner).head
    }
  }

  def onDatePicked: Unit = {
    date = date_picker.getValue.toString.split("-")  //year-month-day
    val verData = Calendar.setDateTime(date(2).toInt, date(1).toInt, date(0).toInt, 0, min).get
    if (Calendar.isFirst(verData, FxApp.today)) insert_valid_date.setVisible(true) else insert_valid_date.setVisible(false)
  }

  def onSubmit: Unit = {
    if (hour_text == "" || minute_picker.getValue == null || practitioner_box.getValue == null || specialty_box.getValue == null || date_picker.getValue == null || hour_text.getText.toInt < 0 || hour_text.getText.toInt > 23) {
      error_label.setVisible(true)
    }
    else {
      val verData = Calendar.setDateTime(date(2).toInt, date(1).toInt, date(0).toInt, 0, min).get
      if (Calendar.isFirst(verData, FxApp.today)) {
        insert_valid_date.setVisible(true)
      }
      else {
        val hour = hour_text.getText
        val minute = minute_picker.getValue

        if (minute == 0) min = MinuteENUM.Zero else min = MinuteENUM.Trinta
        val dateHour = Calendar.setDateTime(date(2).toInt, date(1).toInt, date(0).toInt, hour.toInt, min).get

        if (hour.length > 0 && minute != -1) {

          if (x1.isDefined && FxApp.mf1.patl.people.contains(FxApp.user)) {

            if (!FxApp.mf1.wtl.availableSlot(dateHour, pract)) {
              date_label.setText("The date and time you chose are unavailable, please pick a different one.")
              return
            }
            else {
              val new_mf = FxApp.mf1.addAppointmentWL(FxApp.user, dateHour, pract, Some(0), false).get
              FxApp.mf1 = new_mf
            }

          } else {

            if (!FxApp.mf2.fexl.checkDateAvailability(dateHour)) {
              insert_valid_date.setText("The date and time you chose are unavailable, please pick a different one.")
              insert_valid_date.setMinWidth(Region.USE_PREF_SIZE)
              insert_valid_date.setVisible(true)
              return
            }
            else {
              val new_mf = FxApp.mf2.addAppointmentWL((FxApp.user, dateHour, pract, Some(0), false)).get
              FxApp.mf2 = new_mf
            }
          }

          onAppClicked

        } else {
          error_label.setText("Please complete every field.")
        }

      }
    }
  }

}
