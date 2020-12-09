
import FxApp.{mf1, mf2}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.Parent
import javafx.scene.control.{Label, TextArea}

import java.net.URL
import java.util.ResourceBundle

class ReceiptsControllerAdmin extends Initializable{


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
  private var text_area: TextArea = _

  def onLogoutClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("LoginControllerAdmin.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    logout_button.getScene().setRoot(mainViewRoot)
  }

  def onHomeClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("HomeControllerAdmin.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    home_button.getScene().setRoot(mainViewRoot)
  }

  def onExamsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("ExamsControllerAdmin.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    exams_button.getScene().setRoot(mainViewRoot)
  }

  def onAppClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("AppointmentsControllerAdmin.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    appointments_button.getScene().setRoot(mainViewRoot)
  }

  def onPrescriptionsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("PrescriptionControllerAdmin.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    prescriptions_button.getScene().setRoot(mainViewRoot)
  }

  def onHistoryClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("HistoryControllerAdmin.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    history_button.getScene().setRoot(mainViewRoot)
  }


  override def initialize(location: URL, resources: ResourceBundle): Unit = { //receipts

    val totalDebt = mf1.getTotalDebt() + mf2.getTotalDebt()

    val pal1 = FxApp.mf1.pal
    val pel1 = FxApp.mf1.pexl



    pal1.appointments.foreach(a => {

      text_area.appendText("Medical facility: 1")
      text_area.appendText("Date: " + Calendar.toString(a._2) + "\n")
      text_area.appendText("Type of procedure: Appointment\n")
      text_area.appendText("Specialty: " + a._3._5.getOrElse("--") + "\n")
      text_area.appendText("Practitioner: " + a._3._1 + "\n")
      text_area.appendText("Cost: " + a._4.getOrElse(0) + "\n")
      text_area.appendText("Debt: " + a._5 + "\n \n")




    })


    pel1.exams.foreach(a => {

      text_area.appendText("Medical facility: 1")

      text_area.appendText("Date: " + Calendar.toString(a._4) + "\n")
      text_area.appendText("Type of procedure: Exam\n")
      text_area.appendText("Specialty: " + a._2._5.getOrElse("--") + "\n")
      text_area.appendText("Practitioner: " + a._2._1 + "\n")
      text_area.appendText("Cost: " + a._5.getOrElse(0) + "\n")
      text_area.appendText("Debt: " + a._7 + "\n \n")




    })



    val pal2 = FxApp.mf2.pal
    val pel2 = FxApp.mf2.pexl

    pal2.appointments.foreach(a => {

      text_area.appendText("Medical facility: 2")
      text_area.appendText("Date: " + Calendar.toString(a._2) + "\n")
      text_area.appendText("Type of procedure: Appointment\n")
      text_area.appendText("Specialty: " + a._3._5.getOrElse("--") + "\n")
      text_area.appendText("Practitioner: " + a._3._1 + "\n")
      text_area.appendText("Cost: " + a._4.getOrElse(0) + "\n")
      text_area.appendText("Debt: " + a._5 + "\n \n")



    })

    pel2.exams.foreach(a => {

      text_area.appendText("Medical facility: 2")
      text_area.appendText("Date: " + Calendar.toString(a._4) + "\n")
      text_area.appendText("Type of procedure: Exam\n")
      text_area.appendText("Specialty: " + a._2._5.getOrElse("--") + "\n")
      text_area.appendText("Practitioner: " + a._2._1 + "\n")
      text_area.appendText("Cost: " + a._5.getOrElse(0) + "\n")
      text_area.appendText("Debt: " + a._7 + "\n \n")



    })
    text_area.appendText(" \n ------------------------------------------- \n \nThe total debt is: : " + totalDebt) //not working, suspect its the getTotaldebt func
  }






}