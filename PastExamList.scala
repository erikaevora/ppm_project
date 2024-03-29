import ExamList.{Exam, Exams}

case class PastExamList(override val medicalFacility: String, override val exams: Exams) extends ExamList(medicalFacility, exams) {
  def toPastExamList(): PastExamList = PastExamList.toPastExamList(this)
  def addExam(e: Exam, today: Calendar): Option[PastExamList] = PastExamList.addExam(this, e, today)
}

object PastExamList {

  def addExam(pel: PastExamList, e: Exam, today: Calendar): Option[PastExamList] = {
    if (validateInsertion(e, today)) {
     Some(new PastExamList(pel.medicalFacility, pel.exams :+ e))
    }
    else {
      None
    }
  }

  def toPastExamList(el: ExamList): PastExamList = {
    new PastExamList(el.medicalFacility, el.exams)
  }

  def validateInsertion(e: Exam, today: Calendar): Boolean = {
    if (Calendar.isFirst(e._4, today)) true else false
  }
}