import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, Label, PasswordField, TextField}
import javafx.scene.image.ImageView
import javafx.stage
import javafx.stage.{Modality, Stage}

class LoginController {

  @FXML
  private var nif: TextField = _

  @FXML
  private var password: PasswordField = _

  @FXML
  private var register_button: Button = _

  @FXML
  private var login_button: Button = _

  @FXML
  private var error_label: Label = _


  def onRegisterClicked: Unit = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("RegisterController.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    register_button.getScene().setRoot(mainViewRoot)

  }

  def onLoginClicked: Unit = {

    if (password.getText == "admin" && nif.getText == "admin") {
      val fxmlLoader = new FXMLLoader(getClass.getResource("HomeControllerAdmin.fxml"))
      val mainViewRoot: Parent = fxmlLoader.load()

      login_button.getScene().setRoot(mainViewRoot)
    }
    val p1 = FxApp.mf1.searchPersonNIFClient(nif.getText)
    val p2 = FxApp.mf2.searchPersonNIFClient(nif.getText)

    val d1 = FxApp.mf1.searchPersonNIFDoctor(nif.getText)
    val d2 = FxApp.mf2.searchPersonNIFDoctor(nif.getText)

    if ( (p1.nonEmpty && p1.get._7 == password.getText) || (p2.nonEmpty && p2.get._7 == password.getText) ){

      if(p1.nonEmpty && p1.get._7 == password.getText) FxApp.user = p1.get
      else FxApp.user = p2.get

      val fxmlLoader = new FXMLLoader(getClass.getResource("HomeController.fxml"))
      val mainViewRoot: Parent = fxmlLoader.load()

      login_button.getScene().setRoot(mainViewRoot)
    } else {

      if ((d1.nonEmpty && d1.get._7 == password.getText) || (d2.nonEmpty && d2.get._7 == password.getText)) {

        if (d1.nonEmpty && d1.get._7 == password.getText) FxApp.user = d1.get
        else FxApp.user = d2.get

        val fxmlLoader = new FXMLLoader(getClass.getResource("HomeControllerDoctor.fxml"))
        val mainViewRoot: Parent = fxmlLoader.load()

        login_button.getScene().setRoot(mainViewRoot)
      }

      else {
        error_label.setText("Login failed, NIF and/or password are incorrect.")
      }
    }
  }

}
