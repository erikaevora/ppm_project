import AppointmentList.Appointment
import ExamList.{Bill, Client, DateTime, Exam, Exams, Practitioner, Result, Specialty}
import PersonList.{Name, People, Person}
import PrescriptionList.Prescription
import SpecialtyENUM.SpecialtyENUM

class ExamList(val medicalFacility: String, val exams: Exams) extends Serializable {
  def editExamResult(ex: Exam, r: Result): ExamList = ExamList.editExamResult(this, ex, r)
  def getPatientExams(p: Person): Exams = ExamList.getPatientExams(exams, p)
  def filterExamPatientDate(dt: Calendar, p: Client): Exams = ExamList.filterExamPatientDate(this, dt, p)
  def filterExamDoc(doc: Practitioner): Exams = ExamList.filterExamDoc(this, doc)
  def containsExam(ex: Exam): Boolean = ExamList.containsExam(this, ex)
  def filterExamDoctorDate(dt: Calendar, p: Practitioner): Exams = ExamList.filterExamDoctorDate(this, dt, p)
  def findExam(cli: Name, doc: Name, dt: DateTime): Option[Exam] = ExamList.findExam(this, cli, doc, dt)
  def payDebt(ex: Exam): Option[ExamList] = ExamList.payDebt(this, ex)
  def coronaDiscount(): Option[ExamList] = ExamList.coronaDiscount(this)
  def thirdDiscount(): Option[ExamList] = ExamList.thirdDiscount(this)
  def fourthDiscount(): Option[ExamList] = ExamList.fourthDiscount(this)
}


object ExamList {
  type Client = Person
  type Practitioner = Person
  type Specialty = SpecialtyENUM
  type DateTime = Calendar
  type Bill = Option[Float]
  type Result = Option[String]
  type isPaid = Boolean

  type Exam = (Client, Practitioner, Specialty, DateTime, Bill, Result, isPaid)
  type Exams = List[Exam]


  //lista de exames do paciente p
  def getPatientExams(exams: Exams, c: Client): Exams = {
    exams match {
      case Nil => Nil
      case x :: xs => if (x._1 == c) x :: getPatientExams(xs, c) else getPatientExams(xs, c)
    }
  }

  def findExam(el: ExamList, cli: Name, doc: Name, dt: DateTime): Option[Exam] = {
    el.exams.find(x => x._1._1.equals(cli) && x._2._1.equals(doc) && x._4.equals(dt))
  }

  def editExamResult(el: ExamList, ex: Exam, r: Result): ExamList = {
    val exm = (ex._1, ex._2, ex._3, ex._4, ex._5, r, ex._7)
    val update = el.exams updated (el.exams indexOf ex, exm)
    new ExamList(el.medicalFacility, update)
  }

  //filtra os exames de um paciente p numa determinada data dt
  def filterExamPatientDate(el: ExamList, dt: Calendar, p: Practitioner): Exams = {
    val list = getPatientExams(el.exams, p)
    list filter (x => x._4.equals(dt))
  }

  def filterExamDoctorDate(el: ExamList, dt: Calendar, p: Practitioner): Exams = {
    el.exams filter (x => (x._2._3.equals(p._3) && Calendar.sameDay(x._4, dt)))
  }

  def filterExamDoc(el: ExamList, doc: Practitioner): Exams = {
    el.exams filter (x => x._2._3.equals(doc._3))
  }

  def containsExam(el: ExamList, ex: Exam): Boolean = {
    if (el.exams.contains(ex)) true else false
  }

  def payDebt(el: ExamList, ex: Exam): Option[ExamList] = {
    if (el.exams.contains(ex) && !ex._7) {
      val paid = (ex._1, ex._2, ex._3, ex._4, ex._5, ex._6, true)
      val update = el.exams updated (el.exams indexOf ex, paid)
      Some(new ExamList(el.medicalFacility, update))
    }
    else {
      None
    }
  }

  def coronaDiscount(el: ExamList): Option[ExamList] = {
    val updated = el.exams map (x => if (isCorona(x) && isUnpaid(x)) (x._1, x._2, x._3, x._4, oneHalf(x), x._6, x._7) else x)
    Some(new ExamList(el.medicalFacility, updated))
  }

  def thirdDiscount(el: ExamList): Option[ExamList] = {
    val updated = el.exams map (x => if (isCorona(x) && isUnpaid(x)) (x._1, x._2, x._3, x._4, oneThird(x), x._6, x._7) else x)
    Some(new ExamList(el.medicalFacility, updated))
  }

  def fourthDiscount(el: ExamList): Option[ExamList] = {
    val updated = el.exams map (x => if (isCorona(x) && isUnpaid(x)) (x._1, x._2, x._3, x._4, oneFourth(x), x._6, x._7) else x)
    Some(new ExamList(el.medicalFacility, updated))
  }

  def isCorona(ex: Exam): Boolean = {
    ex._6 match {
      case None => false
      case _ => {
        if(ex._6.get == "covid-19") true else false
      }
    }
  }

  def isUnpaid(ex: Exam): Boolean = {
    if (ex._7) true else false
  }

  def discountBill(ex: Exam)(factor: Int): Bill = {
    ex._5 match {
      case None => None
      case _ => Some(ex._5.get / factor)
    }
  }

  def oneHalf(ex: Exam): Bill = discountBill(ex)(2)
  def oneThird(ex: Exam): Bill = discountBill(ex)(3)
  def oneFourth(ex: Exam): Bill = discountBill(ex)(4)

}