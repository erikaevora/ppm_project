import FxApp.{mf1, mf2}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.Parent
import javafx.scene.control.{Label, TextArea}

import java.net.URL
import java.util.ResourceBundle

class AppointmentsControllerAdmin extends Initializable{


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

  def onReceiptsClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("ReceiptsControllerAdmin.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    receipts_button.getScene().setRoot(mainViewRoot)
  }


  override def initialize(location: URL, resources: ResourceBundle): Unit = { //to change


    val wtl1 = FxApp.mf1.wtl
    val pal1 = FxApp.mf1.pal



    wtl1.appointments.foreach(a => {

      text_area.appendText("Medical facility: 1\n")
      text_area.appendText("Date: " + Calendar.toString(a._2) + "\n")
      text_area.appendText("Specialty: " + a._3._5.getOrElse("--")+ "\n")
      text_area.appendText("Practitioner: " + a._3._1 + "\n")
      text_area.appendText("Cost: " + a._4 + "\n \n")




    })


    pal1.appointments.foreach(a => {

      text_area.appendText("Medical facility: 1\n")
      text_area.appendText("Date: " + Calendar.toString(a._2) + "\n")
      text_area.appendText("Specialty: " + a._3._5.getOrElse("--") + "\n")
      text_area.appendText("Practitioner: " + a._3._1 + "\n")
      text_area.appendText("Cost: " + a._4 + "\n")
      text_area.appendText("Debt: " + a._5 + "\n \n")




    })



    val wtl2 = FxApp.mf2.wtl
    val pal2 = FxApp.mf2.pal

    wtl2.appointments.foreach(a => {

      text_area.appendText("Medical facility: 1\n")
      text_area.appendText("Date: " + Calendar.toString(a._2) + "\n")
      text_area.appendText("Specialty: " + a._3._5.getOrElse("--") + "\n")
      text_area.appendText("Practitioner: " + a._3._1 + "\n")
      text_area.appendText("Cost: " + a._4 + "\n \n")




    })

    pal2.appointments.foreach(a => {

      text_area.appendText("Medical facility: 1\n")
      text_area.appendText("Date: " + Calendar.toString(a._2) + "\n")
      text_area.appendText("Specialty: " + a._3._5.getOrElse("--") + "\n")
      text_area.appendText("Practitioner: " + a._3._1 + "\n")
      text_area.appendText("Cost: " + a._4 + "\n")
      text_area.appendText("Debt: " + a._5 + "\n \n")



    })
  }






}
