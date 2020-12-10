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
