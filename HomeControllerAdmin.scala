import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Parent
import javafx.scene.control.{Button, Label}

class HomeControllerAdmin {

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
}
