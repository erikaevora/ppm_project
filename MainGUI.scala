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
  var mf2: MedicalFacility = Try(IO.readFromFile("CC Mem Martins")).getOrElse(PopulateFacility.getMedicalFacility2())

  mf1.fexl.exams.foreach (e => {
    val new_mf = mf1.addExamP (e, today)
    new_mf match {
      case Some(new_mf) => {
        mf1 = new_mf
        mf1 = mf1.deleteExamF(e)
      }
      case None =>
    }
  })
  mf1.wtl.appointments.foreach(a => {
    val new_mf = mf1.addAppointmentPAL(a, today)
    if (new_mf.isDefined) {
      mf1 = new_mf.get
      mf1 = mf1.deleteAppointmentWL(a).get
    }
  })

  mf2.fexl.exams.foreach (e => {
    val new_mf = mf2.addExamP (e, today)
    if (new_mf.isDefined) {
      mf2 = new_mf.get
      mf2 = mf2.deleteExamF(e)
    }
  })
  mf2.wtl.appointments.foreach(a => {
    val new_mf = mf2.addAppointmentPAL(a, today)
    if (new_mf.isDefined) {
      mf2 = new_mf.get
      mf2 = mf2.deleteAppointmentWL(a).get
    }
  })


  def saveProgress(): Unit = {
      Try(IO.writeToFile(mf1.nome, mf1)).getOrElse(println("Code: 2635"))
      Try(IO.writeToFile(mf2.nome, mf2)).getOrElse(println("Code: 2635"))
  }

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[MainGUI], args:_*)
  }

}