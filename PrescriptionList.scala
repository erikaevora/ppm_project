import AppointmentList.Specialty
import PersonList.Person
import PrescriptionList.{Bill, Client, Description, Doctor, Prescription, Prescriptions}

import scala.annotation.tailrec

case class PrescriptionList(medicalFacility: String, prescriptions: Prescriptions) {
  def addPrescription(psc: Prescription): PrescriptionList = PrescriptionList.addPrescription(this, psc)
  def deletePrescription(psc: Prescription): Option[PrescriptionList] = PrescriptionList.deletePrescription(this, psc)
  def alterPrescription(p: Prescription, b: Bill, d: Description): Option[PrescriptionList] = PrescriptionList.alterPrescription(this, p, b, d)
  def getPatientPrescription(p: Client): Prescriptions = PrescriptionList.getPatientPrescription(this, p)
  def getDoctorPrescription(doc: Doctor): Prescriptions = PrescriptionList.getDoctorPrescription(this, doc)
  def getSpecialtyPrescription(sp: Specialty): Prescriptions = PrescriptionList.getSpecialtyPrescription(this, sp)
  def payDebt(prs: Prescription): Option[PrescriptionList] = PrescriptionList.payDebt(this, prs)
}

object PrescriptionList {
  type DateTime = Calendar
  type Bill = Option[Float]
  type Client = Person
  type Doctor = Person
  type Description = String
  type isPaid = Boolean

  type Prescription = (Client, Doctor, DateTime, Description, Bill, isPaid)
  type Prescriptions = List[Prescription]


  //alteração para github
//  devovler PRESCRIPTIONS ou PrescriptionList????
  def addPrescription(pl: PrescriptionList, psc: Prescription): PrescriptionList = {
    val prescs = psc :: pl.prescriptions
    new PrescriptionList(pl.medicalFacility, prescs)
  }

  def deletePrescription(pl: PrescriptionList, psc: Prescription): Option[PrescriptionList] = {
    if (pl.prescriptions.contains(psc)) {
      val presc = pl.prescriptions filter (x => !x.equals(psc))
      Some(new PrescriptionList(pl.medicalFacility, presc))
    }
    else {
      None
    }

  }


  def alterPrescription(pl: PrescriptionList, p: Prescription, b: Bill, d: Description): Option[PrescriptionList] = {
    if (pl.prescriptions.contains(p)) {
      val psc = (p._1, p._2, p._3, d, b, p._6)
      val presclst = pl.prescriptions updated(pl.prescriptions indexOf p, psc)
      val n = new PrescriptionList(pl.medicalFacility, presclst)
      Some(n)
    }
    else {
      None
    }
  }

  def getPrescriptionsByParams(pl: PrescriptionList, f: Prescription => Boolean): Prescriptions = {
    @tailrec
    def loop(pl: Prescriptions, f: Prescription => Boolean, resp: Prescriptions): Prescriptions = {
      pl match {
        case Nil => resp
        case x :: tail => if (f(x)) loop(tail, f, List(x) ++ resp) else loop(tail, f, resp)
      }
    }
    loop(pl.prescriptions, f, List())
  }

  def getPatientPrescription(pl: PrescriptionList, p: Client): Prescriptions = {
    getPrescriptionsByParams(pl, prescriptionEqualsPatient(p))
  }

  def getDoctorPrescription(pl: PrescriptionList, doc: Doctor): Prescriptions = {
    getPrescriptionsByParams(pl, prescriptionEqualsDoctor(doc))
  }

  def getSpecialtyPrescription(pl: PrescriptionList, sp: Specialty): Prescriptions = {
    getPrescriptionsByParams(pl, prescriptionEqualsSpecialty(sp))
  }

  def prescriptionEqualsPatient(p: Client)(psc: Prescription): Boolean = {
    if (psc._1 == p) true else false
  }

  def prescriptionEqualsDoctor(doc: Doctor)(psc: Prescription): Boolean = {
    if (psc._2 == doc) true else false
  }

  def prescriptionEqualsSpecialty(sp: Specialty)(psc: Prescription): Boolean = {
    if (psc._2._5.getOrElse(None) == sp) true else false
  }

  def payDebt(pl: PrescriptionList, prs: Prescription): Option[PrescriptionList] = {
    if (pl.prescriptions.contains(prs) && !prs._6) {
      val paid = (prs._1, prs._2, prs._3, prs._4, prs._5, true)
      val update = pl.prescriptions updated (pl.prescriptions indexOf prs, paid)
      Some(new PrescriptionList(pl.medicalFacility, update))
    }
    else {
      None
    }
  }


//  def getPrescriptionsByParams(pl: PrescriptionList, t: Any, f: (Prescription, Any) => Boolean): Prescriptions = {
//    @tailrec
//    def loop(pl: Prescriptions, t: Any, f: (Any, Any) => Boolean, resp: Prescriptions): Prescriptions = {
//      pl match {
//        case Nil => resp
//        case x :: tail => if (f(x, t)) loop(tail, t, f, List(x) ++ resp) else loop(tail, t, f, resp)
//      }
//    }
//    loop(pl.prescriptions, t, f:(Any, Any) => Boolean, List())
//  }


//  def getPatientPrescriptions(pl: PrescriptionList, p: Patient): Prescriptions = {
//    @tailrec
//    def loop(pl: Prescriptions, p: Patient, resp: Prescriptions): Prescriptions = {
//      pl match {
//        case Nil => resp
//        case x :: tail => if (x._1 == p) loop(tail, p, List(x) ++ resp) else loop(tail, p, resp)
//      }
//    }
//    loop(pl.prescriptions, p, List())
//  }


//  def getSpecialtyPrescriptions(pl: PrescriptionList, sp: SpecialtyENUM): Prescriptions = {
//    @tailrec
//    def loop(pl: Prescriptions, doc: Doctor, resp: Prescriptions): Prescriptions = {
//      pl match {
//        case Nil => resp
//        case x :: tail => if (x._2 == doc) loop(tail, doc, List(x) ++ resp) else loop(tail, doc, resp)
//      }
//    }
//    loop(pl.prescriptions, doc, List())
//  }


//  def main(args: Array[String]): Unit = {
//    
//    val cal1 = Calendar.setDateTime(29, 2, 2000, 20, MinuteENUM.Trinta)
//    val cal2 = Calendar.setDateTime(12, 5, 2001, 9, MinuteENUM.Trinta)
//    val cal3 = Calendar.setDateTime(2, 12, 2001, 8, MinuteENUM.Zero)
//    
//    val hoje = Calendar.setDateTime(20, 11, 2020, 20, MinuteENUM.Trinta)
//    val amanha = Calendar.setDateTime(21, 11, 2020, 9, MinuteENUM.Trinta)
//    val natal = Calendar.setDateTime(25, 12, 2020, 8, MinuteENUM.Zero)
//
////    Falta uma string
//    val cli1: Patient = ("erica", cal2, "123456", Option("13456"), Option(1))
//    val cli2: Patient = ("maria", cal3, "12347", Option("12345"), Option(2))
//    val cli3: Patient = ("antonio", cal1, "123458", Option("12356"), Option(3))
//
//    val doc1: Practitioner = ("to", cal1, "NIF", Option("phonenr"), SpecialtyENUM.GP)
//    val doc2: Practitioner = ("to", cal1, "NIF", Option("phonenr"), SpecialtyENUM.PimpLord)
//
//
//    val presc1: Prescription = (cli1, doc1, natal, "descrição", Option(80))
//    val presc2: Prescription = (cli2, doc1, natal, "descrição2", Option(802))
//    val prescs: Prescriptions = List(presc1)
//    val pl = new PrescriptionList("CC Rio de Mouro", prescs)
//
//    print(pl.prescriptions)
////    print("\n")
////    print("\n")
////    print(pl.deletePrescription(presc1).prescriptions)
////    print(pl.deletePrescription(presc2).prescriptions)
//    print("\n")
//    print("\n")
////    print(pl.alterPrescription(presc1, Option(7), "alteração").prescriptions)
////    print(pl.addPrescription(presc1).addPrescription(presc2).getSpecialtyPrescription(SpecialtyENUM.PimpLord))
////    print(pl.getSpecialtyPrescription(SpecialtyENUM.GP))
////    print(pl.getPatientPrescription(cli1))
//    print(pl.getDoctorPrescription(doc2))
//  }

}