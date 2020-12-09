import java.net.URL
import java.util.ResourceBundle

import PersonList.{NIF, Person, changePhoneNr}
import javafx.application.Platform
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.Button
import javafx.scene.Parent
import javafx.scene.control.{Label, TextArea}
import javafx.stage.Stage

class EditPhoneNumber extends Initializable {

  @FXML
  private var warning_text: Label = _

  @FXML
  private var phone_text: TextArea = _

  @FXML
  private var confirm_phone_button: Button = _

  private val mf1 = FxApp.mf1
  private val mf2 = FxApp.mf2
  private val usr = FxApp.user

  override def initialize(location: URL, resources: ResourceBundle): Unit = {


  }

  def onConfirmChangeClicked: Unit = {
    val nr = phone_text.getText
    if (confirmNumber(nr)) {
      if(FxApp.mf1.containsPersonClient(usr) || FxApp.mf1.containsPersonDoctor(usr)) {
        FxApp.user = (usr._1, usr._2, usr._3, Some(nr), usr._5, usr._6, usr._7, usr._8)
        FxApp.mf1 = mf1.changePhoneNr(usr, nr).get
      }
      if(FxApp.mf2.containsPersonClient(usr) || FxApp.mf2.containsPersonDoctor(usr)) {
        FxApp.user = (usr._1, usr._2, usr._3, Some(nr), usr._5, usr._6, usr._7, usr._8)
        FxApp.mf2 = mf2.changePhoneNr(usr, nr).get
      }
      warning_text.setVisible(false)
      confirm_phone_button.getScene().getWindow.hide()
    }
    else {
      warning_text.setVisible(true)
    }
  }

  def confirmNumber(nr: String): Boolean = {
    if (nr.length != 9 && nr.forall(_.isDigit)) false else true
  }
}
