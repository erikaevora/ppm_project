import scala.io.Source
import java.io._


object IO {

  def readFromFile(facility: String): MedicalFacility = {
    val in = new ObjectInputStream(new FileInputStream(facility + ".dat.txt"))
    in.readObject().asInstanceOf[MedicalFacility]
  }


  def writeToFile(facility: String, content: MedicalFacility): Unit = {
    val out = new ObjectOutputStream(new FileOutputStream(facility + ".dat.txt"))
    out.writeObject(content)
    out.close
  }
}


//  def readAppointmentsFromFile(): AppointmentList = {
//    val in = new ObjectInputStream(new FileInputStream("appointments.dat.txt"))
//    in.readObject().asInstanceOf[AppointmentList]
//  }
//
//  def readExamsFromFile(): ExamList = {
//    val in = new ObjectInputStream(new FileInputStream("exams.dat.txt"))
//    in.readObject().asInstanceOf[ExamList]
//  }
//
//  def readReceiptsFromFile(): ReceiptList = {
//    val in = new ObjectInputStream(new FileInputStream("receipts.dat.txt"))
//    in.readObject().asInstanceOf[ReceiptList]
//  }
//
//  def readPatientsFromFile(): PatientList = {
//    val in = new ObjectInputStream(new FileInputStream("patients.dat.txt"))
//    in.readObject().asInstanceOf[PatientList]
//  }
//
//  def readPractitionersFromFile(): PractitionerList = {
//    val in = new ObjectInputStream(new FileInputStream("practitioners.dat.txt"))
//    in.readObject().asInstanceOf[PractitionerList]
//  }

//val home = System.getProperty("user.home")
//readFromFile("dados.txt") //relative path
