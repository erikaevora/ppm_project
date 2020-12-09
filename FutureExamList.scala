import ExamList.{Exam, Exams}

case class FutureExamList(override val medicalFacility: String, override val exams: Exams) extends ExamList(medicalFacility, exams) {
  def addExam(e: Exam): FutureExamList = FutureExamList.addExam(this, e)
  def deleteExam(e: Exam): ExamList = FutureExamList.deleteExam(this, e)
  def changeExamDate(e: Exam, dt: Calendar): Option[ExamList] = FutureExamList.changeExamDate(this, e, dt)
  def checkDateAvailability(dt: Calendar): Boolean = FutureExamList.checkDateAvailability(this, dt)
  def toFutureExamList(): FutureExamList = FutureExamList.toFutureExamList(this)
}

object FutureExamList {

  def addExam(fel: FutureExamList, e: Exam): FutureExamList = {
    if (!fel.exams.contains(e)) {
      val nl = fel.exams :+ e
      new FutureExamList(fel.medicalFacility, nl)
    }
    else {
      fel
    }
  }

  //desmarcar exame
  def deleteExam(el: ExamList, e: Exam): ExamList = {
    val exams = el.exams filter (x => !x.equals(e))
    new ExamList(el.medicalFacility, exams)
  }

  //verifica se nao ha nenhum exame marcado na data dt
  def checkDateAvailability(el: ExamList, dt: Calendar): Boolean = {
    val list = el.exams filter (e => e._4 == dt)
    list.isEmpty //pode marcar exame se nao houver nenhum exame marcado na data dt
  }

  //altera a data do exame e para dt, se esta estiver disponivel
  def changeExamDate(el: ExamList, e: Exam, dt: Calendar): Option[ExamList] = {
    if (checkDateAvailability(el, dt) && el.exams.contains(e)) {
      val new_exam = (e._1, e._2, e._3, dt, e._5, e._6, false)
      val update = el.exams updated(el.exams indexOf e, new_exam)
      Some(new ExamList(el.medicalFacility, update))
    } else None
  }

  def toFutureExamList(el: ExamList): FutureExamList = {
    new FutureExamList(el.medicalFacility, el.exams)
  }
}
