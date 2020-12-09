import PersonList.Person
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import scala.util.Try

class MainGUI extends Application {

  @Override
  def start(primaryStage: Stage): Unit = {
    val root: Parent = FXMLLoader.load(getClass.getResource("LoginController.fxml"))
//    primaryStage.initStyle(StageStyle.UNIFIED)
    primaryStage.setTitle("MyHealth App")
    primaryStage.setResizable(false)

    val scene = new Scene(root, 900, 610)
    primaryStage.setScene(scene)
    primaryStage.show()

  }

  override def stop(): Unit = {
    println("Closing...")
    FxApp.saveProgress()
  }

}

object FxApp{
  val today = Calendar.getCurrentTime()

  var user: Person = ("", Calendar.getCurrentTime(), "", None, None, None, "", true)

  var mf1: MedicalFacility = Try(IO.readFromFile("CC Rio de Mouro")).getOrElse(PopulateFacility.getMedicalFacility1())
  println(mf1)
  var mf2: MedicalFacility = Try(IO.readFromFile("CC Mem Martins")).getOrElse(PopulateFacility.getMedicalFacility2())
  println("MedicalFacility2 loaded")

  private val examsf1 = mf1.fexl.exams
  private val appsf1 = mf1.wtl.appointments
  private val examsf2 = mf2.fexl.exams
  private val appsf2 = mf2.wtl.appointments

  println(examsf1.length)
  println(appsf1.length)
  println(examsf2.length)
  println(appsf2.length)
  examsf1.foreach (e => {
    println("cheguei aqui 2")
    val new_mf = mf1.addExamP (e, today)
    new_mf match {
      case Some(new_mf) => {
        println("uma coisa qualquer")
        mf1 = new_mf
        mf1 = mf1.deleteExamF(e)
      }
      case None =>
    }
  })
  appsf1.foreach(a => {
    val new_mf = mf1.addAppointmentPAL(a, today)
    if (new_mf.isDefined) {
      mf1 = new_mf.get
      mf1 = mf1.deleteAppointmentWL(a).get
    }
  })

  examsf2.foreach (e => {
    val new_mf = mf2.addExamP (e, today)
    if (new_mf.isDefined) {
      mf2 = new_mf.get
      mf2 = mf2.deleteExamF(e)
    }
  })
  appsf2.foreach(a => {
    val new_mf = mf2.addAppointmentPAL(a, today)
    if (new_mf.isDefined) {
      mf2 = new_mf.get
      mf2 = mf2.deleteAppointmentWL(a).get
    }
  })


  def saveProgress(): Unit = {
      Try(IO.writeToFile(mf1.nome, mf1)).getOrElse(println("Erro a gravar ficheiro"))
      Try(IO.writeToFile(mf2.nome, mf2)).getOrElse(println("Erro a gravar ficheiro"))
  }

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[MainGUI], args:_*)
  }

}