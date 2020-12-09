import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.TextArea

class NextExamDoc extends Initializable {

  @FXML
  private var exam_text: TextArea = _

  private val mf1 = FxApp.mf1
  private val mf2 = FxApp.mf2
  private val usr = FxApp.user

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    val exl = mf1.filterExamDocF(usr).sortWith((a, b) => Calendar.isFirst(a._4, b._4))
    val exl2 = mf2.filterExamDocF(usr).sortWith((a, b) => Calendar.isFirst(a._4, b._4))
    if(exl.length > 0) {
      val ex = exl.head
      exam_text.appendText("Next Exam: " + "\n")
      exam_text.appendText("Date and Time: " + Calendar.toString(ex._4) + "\n")
      exam_text.appendText("Patient: " + ex._1._1 + " - " + Calendar.toString(ex._1._2) + "\n")
    }
    if(exl2.length > 0) {
      val ex = exl2.head
      exam_text.appendText("Next Exam: " + "\n")
      exam_text.appendText("Date and Time: " + Calendar.toString(ex._4) + "\n")
      exam_text.appendText("Patient: " + ex._1._1 + " - " + Calendar.toString(ex._1._2) + "\n")
    }
    if(exl.length == 0 && exl2.length == 0) {
      exam_text.setText("No appointments scheduled for the near future...")
    }
  }
}
