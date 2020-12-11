import AppointmentList.{Appointment, Appointments, DateTime, Doctor, Specialty}
import ExamList.{Client, Exam, Exams, Result}
import FutureExamList.toFutureExamList
import PastAppointmentsList.toPastAppointmentList
import PastExamList.toPastExamList
import PersonList.{NIF, Name, People, Person, isDoctor}
import PrescriptionList.{Bill, Description, Prescription, Prescriptions}
import ReceiptList.Receipts
import SpecialtyENUM.SpecialtyENUM
import WaitingList.toWaitingList

import scala.annotation.tailrec

case class MedicalFacility(nome: String, wtl: WaitingList, pal: PastAppointmentsList, docl: PersonList, patl: PersonList, prescl: PrescriptionList, fexl: FutureExamList, pexl: PastExamList) extends Serializable{

  def getSpecialties(): List[SpecialtyENUM] = MedicalFacility.getSpecialties()

  def getAllReceipts(): Receipts = MedicalFacility.getAllReceipts(this)
  def getAllUnpaidReceipts(): Receipts = MedicalFacility.getAllUnpaidReceipts(this)
  def getAllPaidReceipts(): Receipts = MedicalFacility.getAllPaidReceipts(this)
  def getTotalDebt(): Float = MedicalFacility.getTotalDebt(this)

  def searchPerson(name: String): Option[Person] = MedicalFacility.searchPerson(this, name)
  def addPerson(p: Person): Option[MedicalFacility] = MedicalFacility.addPerson(this, p)
  def deletePerson(p: Person): MedicalFacility = MedicalFacility.deletePerson(this, p)
  def changePhoneNr(p: Person, n: String): Option[MedicalFacility] = MedicalFacility.changePersonPhoneNr(this, p, n)
  def searchPersonNIF(nif: NIF): Option[Person] = MedicalFacility.searchPersonNIF(this, nif)
  def searchPersonNIFClient(nif: NIF): Option[Person] = MedicalFacility.searchPersonNIFClient(this, nif)
  def containsPersonClient(p: Person): Boolean = MedicalFacility.containsPersonClient(this, p)
  def containsPersonDoctor(p: Person): Boolean = MedicalFacility.containsPersonDoctor(this, p)

  def specialtyAppointmentListWL(s: Specialty): Appointments = MedicalFacility.specialtyAppointmentListWL(this, s)
  def organizeByNameWL(): Appointments = MedicalFacility.organizeByNameWL(this)
  def findAppointmentWL(nif: NIF, dt: DateTime, sp: Specialty): Option[Appointment] = MedicalFacility.findAppointmentWL(this, nif, dt, sp)
  def deleteAppointmentWL(ap: Appointment): Option[MedicalFacility] = MedicalFacility.deleteAppointmentWL(this, ap)
  def organizeListWL(): MedicalFacility = MedicalFacility.organizeListWL(this)
  def addAppointmentWL(ap: Appointment): Option[MedicalFacility] = MedicalFacility.addAppointmentWL(this, ap)
  def nextAppointmentPatientWL(p: Person): Option[Appointment] = MedicalFacility.nextAppointmentPatientWL(this, p)
  def nextAppointmentPractitionerWL(p: Person): Option[Appointment] = MedicalFacility.nextAppointmentPractitionerWL(this, p)
  def availableSlotWL(dt: DateTime, doc: Person): Boolean = MedicalFacility.availableSlotWL(this, dt, doc)
  def changeAppointmentDateWL(ap: Appointment, date: DateTime): Option[MedicalFacility] = MedicalFacility.changeAppointmentDateWL(this, ap, date)
  def getAllAppointmentsDoc(doc: Doctor): Appointments = MedicalFacility.getAllAppointmentsDoc(this, doc)
  def filterAppointmentsDoc(doc: Doctor): Appointments = MedicalFacility.filterAppointmentsDoc(this, doc)
  def filterAppointmentsDocDate(doc: Doctor, dt: DateTime): Appointments = MedicalFacility.filterAppointmentsDocDate(this, doc, dt)
  def containsAppointmentWL(ap: Appointment): Boolean = MedicalFacility.containsAppointmentWL(this, ap)
  def findAppointmentCombo(cli: Name, doc: Name, dt: DateTime): Option[Appointment] = MedicalFacility.findAppointmentCombo(this, cli, doc, dt)

  def specialtyAppointmentListPAL(s: Specialty): Appointments = MedicalFacility.specialtyAppointmentListPAL(this, s)
  def organizeByNamePAL(): Appointments = MedicalFacility.organizeByNamePAL(this)
  def findAppointmentPAL(nif: NIF, dt: DateTime, sp: Specialty): Option[Appointment] = MedicalFacility.findAppointmentPAL(this, nif, dt, sp)
  def deleteAppointmentPAL(ap: Appointment): Option[PastAppointmentsList] = MedicalFacility.deleteAppointmentPAL(this, ap)
  def organizeListPAL(): MedicalFacility = MedicalFacility.organizeListPAL(this)
  def addAppointmentPAL(ap: Appointment, today: Calendar): Option[MedicalFacility] = MedicalFacility.addAppointmentPAL(this, ap, today)
  def getAllAppointmentsDocPAL(doc: Doctor): Appointments = MedicalFacility.getAllAppointmentsDocPAL(this, doc)
  def payDebtPAL(ap: Appointment): Option[MedicalFacility] = MedicalFacility.payDebtPAL(this, ap)

  def addPrescription(prc: Prescription): MedicalFacility = MedicalFacility.addPrescription(this, prc)
  def deletePrescription(psc: Prescription): Option[MedicalFacility] = MedicalFacility.deletePrescription(this, psc)
  def alterPrescription(p: Prescription, b: Bill, d: Description): Option[MedicalFacility] = MedicalFacility.alterPrescription(this, p, b, d)
  def getPatientPrescription(p: Client): Prescriptions = MedicalFacility.getPatientPrescription(this, p)
  def getDoctorPrescription(doc: Person): Prescriptions = MedicalFacility.getDoctorPrescription(this, doc)
  def getSpecialtyPrescription(sp: Specialty): Prescriptions = MedicalFacility.getSpecialtyPrescription(this, sp)
  def payDebtPRL(prs: Prescription): Option[MedicalFacility] = MedicalFacility.payDebtPRL(this, prs)

  def getPatientExamsF(p: Client): Exams = MedicalFacility.getPatientExamsF(this, p)
  def filterExamPatientDateF(dt: Calendar, p:Client): Exams = MedicalFacility.filterExamPatientDateF(this, dt, p)
  def payDebtFEL(ex: Exam): Option[MedicalFacility] = MedicalFacility.payDebtFEL(this, ex)
//  def filterExamDoc(doc: Doctor): Exams = MedicalFacility.filterExamDoc(this, doc)
  def addExamF(e: Exam): MedicalFacility = MedicalFacility.addExamF(this, e)
  def deleteExamF(e: Exam): MedicalFacility = MedicalFacility.deleteExamF(this, e)
  def changeExamDate(e: Exam, dt: Calendar): Option[MedicalFacility] = MedicalFacility.changeExamDate(this, e, dt)
  def checkDateAvailability(dt: Calendar): Boolean = MedicalFacility.checkDateAvailability(this, dt)
  def filterExamDocF(doc: Doctor): Exams = MedicalFacility.filterExamDocF(this, doc)
  def containsExamF(ex: Exam): Boolean = MedicalFacility.containsExamF(this, ex)
  def findExamFEL(cli: Name, doc: Name, dt: DateTime): Option[Exam] = MedicalFacility.findExamFEL(this, cli, doc, dt)
  def filterExamDoctorDate(dt: Calendar, p: Doctor): Exams = MedicalFacility.filterExamDoctorDate(this, dt, p)

  def getPatientExamsP(p: Person): Exams = MedicalFacility.getPatientExamsP(this, p)
  def editExamResult(ex: Exam, r: Result): MedicalFacility = MedicalFacility.editExamResult(this, ex, r)
  def filterExamPatientDateP(dt: Calendar, p: Client): Exams = MedicalFacility.filterExamPatientDateP(this, dt, p)
  def payDebtP(ex: Exam): Option[MedicalFacility] = MedicalFacility.payDebtP(this, ex)
  def findExamP(cli: Name, doc: Name, dt: DateTime): Option[Exam] = MedicalFacility.findExamP(this, cli, doc, dt)
  def addExamP(e: Exam, today: Calendar): Option[MedicalFacility] = MedicalFacility.addExamP(this, e, today)
  def filterExamDocP(doc: Doctor): Exams = MedicalFacility.filterExamDocP(this, doc)


  object MedicalFacility {

    type Nome = String
    type Morada = String

    // ----------------------------------------- Specialties ------------------------------------------
    def getSpecialties(): List[SpecialtyENUM] = {
      @tailrec
      def loop(plp: People, l: List[SpecialtyENUM]): List[SpecialtyENUM] = {
        plp match {
          case Nil => l
          case x :: tail => {
            if (l.contains(x._5)) loop(tail, l) else loop(tail, x._5.get :: l)
          }
        }
      }
      loop(docl.people, List())
    }

    // ----------------------------------------- Receipts ----------------------------------------------
    def getAllReceipts(mf: MedicalFacility): Receipts = {
      ReceiptList.getAllReceipts(mf.pal, mf.pexl, mf.prescl)
    }
    def getAllUnpaidReceipts(mf: MedicalFacility): Receipts = ReceiptList.getAllUnpaidReceipts(mf.getAllReceipts())
    def getAllPaidReceipts(mf: MedicalFacility): Receipts = ReceiptList.getAllPaidReceipts(mf.getAllReceipts())
    def getTotalDebt(mf: MedicalFacility): Float = ReceiptList.getTotalDebt(mf.getAllReceipts())

    // ----------------------------------------- PersonList --------------------------------------------
    def searchPerson(mf: MedicalFacility, name: String): Option[Person] = {
      mf.docl.searchPerson(name)
    }
    def searchPersonNIF(mf: MedicalFacility, nif: NIF): Option[Person] = mf.docl.searchPersonNIF(nif)
    def searchPersonNIFClient(mf: MedicalFacility, nif: NIF): Option[Person] = mf.patl.searchPersonNIF(nif)

    def addPerson(mf: MedicalFacility, per: Person): Option[MedicalFacility] = {
      if (isDoctor(per)) {
        val d2 = mf.docl.addPerson(per)
        d2 match {
          case None => None
          case _ => Some(new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, d2.get, mf.prescl, mf.fexl, mf.pexl))
        }
      }
      else {
        val p2 = mf.patl.addPerson(per)
        p2 match {
          case None => None
          case _ => Some(new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, p2.get, mf.prescl, mf.fexl, mf.pexl))
        }
      }
    }
    def deletePerson(mf: MedicalFacility, p: Person): MedicalFacility = {
      if (isDoctor(p)) new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl.deletePerson(p), mf.patl, mf.prescl, mf.fexl, mf.pexl)
      else new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, mf.patl.deletePerson(p), mf.prescl, mf.fexl, mf.pexl)
    }
    def changePersonPhoneNr(mf: MedicalFacility, p: Person, n: String): Option[MedicalFacility] = {
      if(isDoctor(p)) {
        val d = mf.docl.changePhoneNr(p, n)
        d match {
          case None => None
          case _ => Some(new MedicalFacility(mf.nome, mf.wtl, mf.pal, d.get, mf.patl, mf.prescl, mf.fexl, mf.pexl))
        }
      }
      else {
        val pat = mf.patl.changePhoneNr(p, n)
        pat match {
          case None => None
//          case _ => Some(new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, mf.patl.changePhoneNr(p, n).get, mf.prescl, mf.fexl, mf.pexl))
          case _ => Some(new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, pat.get, mf.prescl, mf.fexl, mf.pexl))
        }
      }
    }
    def containsPersonClient(mf: MedicalFacility, p: Person): Boolean = mf.patl.containsPerson(p)
    def containsPersonDoctor(mf: MedicalFacility, p: Person): Boolean = mf.docl.containsPerson(p)

    // -------------------------------------------- WaitingList -----------------------------------------------

    def specialtyAppointmentListWL(mf: MedicalFacility, s: Specialty): Appointments = {
      mf.wtl.specialityAppointmentList(s)
    }
    def organizeByNameWL(mf: MedicalFacility): Appointments = {
      mf.wtl.organizeByName()
    }
    def findAppointmentWL(mf: MedicalFacility, nif: NIF, dt: DateTime, sp: Specialty): Option[Appointment] = {
      mf.wtl.findAppointment(nif, dt, sp)
    }
    def deleteAppointmentWL(mf: MedicalFacility, ap: Appointment): Option[MedicalFacility] = {
      val ap2 = mf.wtl.deleteAppointment(ap)
      ap2 match {
        case None => Some(mf)
        case _ => Some(new MedicalFacility(mf.nome, toWaitingList(ap2.get), mf.pal, mf.docl, mf.patl, mf.prescl, fexl, mf.pexl))
      }
    }
    def organizeListWL(mf: MedicalFacility): MedicalFacility = {
      new MedicalFacility(mf.nome, toWaitingList(mf.wtl.organizeList()), mf.pal, mf.docl, mf.patl, mf.prescl, mf.fexl, mf.pexl)
    }
    def addAppointmentWL(mf: MedicalFacility, ap: Appointment): Option[MedicalFacility] = {
      val ap2 = mf.wtl.addAppointment(ap)
      ap2 match {
        case None => Some(mf)
        case _ => Some(new MedicalFacility(mf.nome, toWaitingList(ap2.get), mf.pal, mf.docl, mf.patl, mf.prescl, fexl, mf.pexl))
      }
    }
    def nextAppointmentPatientWL(mf: MedicalFacility, p: Person): Option[Appointment] = {
      val ap2 = mf.wtl.nextAppointmentPatient(p)
      ap2 match {
        case None => None
        case _ => Some(ap2.get)
      }
    }
    def nextAppointmentPractitionerWL(mf: MedicalFacility, p: Person): Option[Appointment] = {
      val ap2 = mf.wtl.nextAppointmentPractitioner(p)
      ap2 match {
        case None => None
        case _ => Some(ap2.get)
      }
    }
    def availableSlotWL(mf: MedicalFacility, dt: DateTime, doc: Person): Boolean = {
      mf.wtl.availableSlot(dt, doc)
    }
    def filterAppointmentsDoc(mf: MedicalFacility, doc: Doctor): Appointments = mf.wtl.filterAppointmentsDoc(doc)
    def filterAppointmentsDocDate(mf: MedicalFacility, doc: Doctor, dt: DateTime): Appointments = mf.wtl.filterAppointmentsDocDate(doc, dt)
    //    def sendSMSWL(dh: DateTime): Unit = wtl.sendSMS(dh)
    def changeAppointmentDateWL(mf: MedicalFacility, ap: Appointment, date: DateTime): Option[MedicalFacility] = {
      val ap2 = mf.wtl.changeAppointmentDate(ap, date)
      ap2 match {
        case None => None
        case _ => Some(new MedicalFacility(mf.nome, toWaitingList(ap2.get), mf.pal, mf.docl, mf.patl, mf.prescl, fexl, mf.pexl))
      }
    }
    def getAllAppointmentsDoc(mf: MedicalFacility, doc: Doctor): Appointments = mf.wtl.getAllAppointmentsDoc(doc)
    def containsAppointmentWL(mf: MedicalFacility, ap: Appointment): Boolean = mf.wtl.containsAppointment(ap)
    def findAppointmentCombo(mf: MedicalFacility, cli: Name, doc: Name, dt: DateTime): Option[Appointment] = mf.wtl.findAppointmentCombo(cli, doc, dt)


    // ----------------------------------------PastAppointmentsList -------------------------------------------
    def specialtyAppointmentListPAL(mf: MedicalFacility, s: Specialty): Appointments = mf.pal.specialityAppointmentList(s)
    def organizeByNamePAL(mf: MedicalFacility): Appointments = mf.pal.organizeByName()
    def findAppointmentPAL(mf: MedicalFacility, nif: NIF, dt: DateTime, sp: Specialty): Option[Appointment] = mf.pal.findAppointment(nif, dt, sp)
    def deleteAppointmentPAL(mf: MedicalFacility, ap: Appointment): Option[PastAppointmentsList] = Option(toPastAppointmentList(mf.pal.deleteAppointment(ap).get))
    def organizeListPAL(mf: MedicalFacility): MedicalFacility = {
      new MedicalFacility(mf.nome, mf.wtl, toPastAppointmentList(mf.pal.organizeList()), mf.docl, mf.patl, prescl, mf.fexl, mf.pexl)
    }
    def addAppointmentPAL(mf: MedicalFacility, ap: Appointment, today: Calendar): Option[MedicalFacility] = {
      val tmp = mf.pal.addAppointment(ap, today)
      tmp match {
        case None => None
        case _ => Some(new MedicalFacility(mf.nome, mf.wtl, tmp.get, mf.docl, mf.patl, prescl, mf.fexl, mf.pexl))
      }
    }
    def payDebtPAL(mf: MedicalFacility, ap: Appointment): Option[MedicalFacility] = {
      val tmp = mf.pal.payDebt(ap)
      tmp match {
        case None => None
        case _ => Some(new MedicalFacility(mf.nome, mf.wtl, toPastAppointmentList(tmp.get), mf.docl, mf.patl, prescl, mf.fexl, mf.pexl))
      }
    }
    def getAllAppointmentsDocPAL(mf: MedicalFacility, doc: Doctor): Appointments = mf.pal.getAllAppointmentsDoc(doc)

    // ------------------------------------------PrescriptionList ---------------------------------------------
    def addPrescription(mf: MedicalFacility, prc: Prescription): MedicalFacility = {
      val tmp = mf.prescl.addPrescription(prc)
      new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, mf.patl, tmp, mf.fexl, mf.pexl)
    }
    def deletePrescription(mf: MedicalFacility, psc: Prescription): Option[MedicalFacility] = {
      val tmp = mf.prescl.deletePrescription(psc)
      tmp match {
        case None => None
        case _ => Some(new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, mf.patl, tmp.get, mf.fexl, mf.pexl))
      }
    }
    def alterPrescription(mf: MedicalFacility, p: Prescription, b: Bill, d: Description): Option[MedicalFacility] = {
      val tmp = mf.prescl.alterPrescription(p, b, d)
      tmp match {
        case None => None
        case _ => Some(new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, mf.patl, tmp.get, mf.fexl, mf.pexl))
      }
    }
    def getPatientPrescription(mf: MedicalFacility, p: Client): Prescriptions = mf.prescl.getPatientPrescription(p)
    def getDoctorPrescription(mf: MedicalFacility, doc: Person): Prescriptions = mf.prescl.getDoctorPrescription(doc)
    def getSpecialtyPrescription(mf: MedicalFacility, sp: Specialty): Prescriptions = mf.prescl.getSpecialtyPrescription(sp)
    def payDebtPRL(mf: MedicalFacility, prs: Prescription): Option[MedicalFacility] = {
      val tmp = mf.prescl.payDebt(prs)
      tmp match {
        case None => None
        case _ => Some(new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, mf.patl, tmp.get, mf.fexl, mf.pexl))
      }
    }

    // ------------------------------------------- FutureExamList ----------------------------------------------
    def getPatientExamsF(mf: MedicalFacility, p: Client): Exams = mf.fexl.getPatientExams(p)
    def filterExamPatientDateF(mf: MedicalFacility, dt: Calendar, p:Client): Exams = mf.fexl.filterExamPatientDate(dt, p)
    def payDebtFEL(mf: MedicalFacility, ex: Exam): Option[MedicalFacility] = {
      val tmp = mf.fexl.payDebt(ex)
      tmp match {
        case None => None
        case _ => Some(new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, mf.patl, mf.prescl, toFutureExamList(tmp.get), mf.pexl))
      }
    }
    def coronaDiscountF(mf: MedicalFacility): Option[MedicalFacility] = {
      val tmp = mf.fexl.coronaDiscount()
      Some(new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, mf.patl, mf.prescl, toFutureExamList(tmp.get), mf.pexl))
    }
    def filterExamDocF(mf: MedicalFacility, doc: Doctor): Exams = mf.fexl.filterExamDoc(doc)
    def addExamF(mf: MedicalFacility, e: Exam): MedicalFacility = {
      val tmp = mf.fexl.addExam(e)
      new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, mf.patl, mf.prescl, tmp, mf.pexl)
    }
    def deleteExamF(mf: MedicalFacility, e: Exam): MedicalFacility = {
      val tmp = mf.fexl.deleteExam(e)
      new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, mf.patl, mf.prescl, toFutureExamList(tmp), mf.pexl)
    }
    def changeExamDate(mf: MedicalFacility, e: Exam, dt: Calendar): Option[MedicalFacility] = {
      val tmp = mf.fexl.changeExamDate(e, dt)
      tmp match {
        case None => None
        case _ => Some(new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, mf.patl, mf.prescl, toFutureExamList(tmp.get), mf.pexl))
      }
    }
    def filterExamDoctorDate(mf: MedicalFacility, dt: Calendar, p: Doctor): Exams = mf.fexl.filterExamDoctorDate(dt, p)
    def checkDateAvailability(mf: MedicalFacility, dt: Calendar): Boolean = mf.fexl.checkDateAvailability(dt)
    def containsExamF(mf: MedicalFacility, ex: Exam): Boolean = mf.fexl.containsExam(ex)
    def findExamFEL(mf: MedicalFacility, cli: Name, doc: Name, dt: DateTime): Option[Exam] = mf.fexl.findExam(cli, doc, dt)

    // ------------------------------------------- PastExamList ----------------------------------------------
    def getPatientExamsP(mf: MedicalFacility, p: Person): Exams = mf.pexl.getPatientExams(p)
    def editExamResult(mf: MedicalFacility, ex: Exam, r: Result): MedicalFacility = {
      val tmp = mf.pexl.editExamResult(ex, r)
      new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, mf.patl, mf.prescl, mf.fexl, toPastExamList(tmp))
    }
    def filterExamPatientDateP(mf: MedicalFacility, dt: Calendar, p: Client): Exams = mf.pexl.filterExamPatientDate(dt, p)
    def payDebtP(mf: MedicalFacility, ex: Exam): Option[MedicalFacility] = {
      val tmp = mf.pexl.payDebt(ex)
      tmp match {
        case None => None
        case _ => Some(new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, mf.patl, mf.prescl, mf.fexl, toPastExamList(tmp.get)))
      }
    }
    def coronaDiscountP(mf: MedicalFacility): Option[MedicalFacility] = {
      val tmp = mf.pexl.coronaDiscount()
      Some(new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, mf.patl, mf.prescl, mf.fexl, toPastExamList(tmp.get)))
    }
    def findExamP(mf: MedicalFacility, cli: Name, doc: Name, dt: DateTime): Option[Exam] = mf.pexl.findExam(cli, doc, dt)
    def addExamP(mf: MedicalFacility, e: Exam, today: Calendar): Option[MedicalFacility] = {
      val tmp = mf.pexl.addExam(e, today)
      tmp match {
        case None => None
        case _ => Some(new MedicalFacility(mf.nome, mf.wtl, mf.pal, mf.docl, mf.patl, mf.prescl, mf.fexl, toPastExamList(tmp.get)))
      }
    }
    def filterExamDocP(mf: MedicalFacility, doc: Doctor): Exams = mf.pexl.filterExamDoc(doc)
  }
}