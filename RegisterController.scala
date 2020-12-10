import java.net.URL
import java.util.ResourceBundle

import PersonList.Person
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, ComboBox, DatePicker, Label, PasswordField, TextField}
import javafx.stage.{Modality, Stage}

import scala.util.{Failure, Success, Try}

class RegisterController extends Initializable{

  @FXML
  private var login_button: Button = _

  @FXML
  private var register_button: Button = _

  @FXML
  private var password_field: PasswordField = _

  @FXML
  private var nif_text: TextField = _

  @FXML
  private var phone_text: TextField = _

  @FXML
  private var name_text: TextField = _

  @FXML
  private var birthdate_picker: DatePicker = _

  @FXML
  private var medFacility_box: ComboBox[String] = _

  @FXML
  private var error_label: Label = _

  private var birthdate: Calendar = Calendar(0, 0, 0, 0, MinuteENUM.Zero)

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    medFacility_box.getItems.addAll(FxApp.mf1.nome, FxApp.mf2.nome)
  }

  def onLoginClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("LoginController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    login_button.getScene().setRoot(mainViewRoot)
  }

  def onRegisterClicked: Unit = {
    val name  = name_text.getText
    val nif = nif_text.getText
    val phone = phone_text.getText
    val password = password_field.getText
    val mf = medFacility_box.getValue

    def getDate(): Try[Array[String]] = {
      Try(birthdate_picker.getValue.toString.split("-"))  //year-month-day
    }

    getDate() match {
      case Success(date) => birthdate = Calendar.setDateTime(date(2).toInt, date(1).toInt, date(0).toInt, 0, MinuteENUM.Zero).get
      case Failure(f) => error_label.setText("Please complete all the fields above.")
        return
    }

    if (name.length > 0 && nif.length == 9 && password.length > 0 && mf != null) {

      if (mf == FxApp.mf1.nome) {
        val new_mf = FxApp.mf1.addPerson((name, birthdate, nif, Some(phone), None, None, password, true))
        new_mf match {
          case None => error_label.setText("Your NIF is already in our database, please try to Login.")
          case _ => {
            FxApp.mf1 = new_mf.get
            onLoginClicked
          }
        }

      } else {
        val new_mf = FxApp.mf2.addPerson((name, birthdate, nif, Some(phone), None, None, password, true))
        new_mf match {
          case None => error_label.setText("Your NIF is already in our database, please try to Login.")
          case _ => {
            FxApp.mf2 = new_mf.get
            onLoginClicked
          }
        }
      }

    } else {

      error_label.setText("Please complete all the fields correctly.")
    }


  }

}
