import AppointmentList.Appointments
import ExamList.Exams
import PersonList.Person
import PrescriptionList.Prescriptions
import ReceiptList.Receipts

import scala.annotation.tailrec


// ---------------------------------------- ReceiptList ---------------------------------------------

case class ReceiptList(mf: MedicalFacility) {
  def getAllReceipts(pal: PastAppointmentsList, pel: PastExamList, pl: PrescriptionList): Receipts = ReceiptList.getAllReceipts(pal, pel, pl)
  def getAllUnpaidReceipts(rcps: Receipts): Receipts = ReceiptList.getAllUnpaidReceipts(rcps)
  def getAllPaidReceipts(rcps: Receipts): Receipts = ReceiptList.getAllPaidReceipts(rcps)
  def getTotalDebt(rcps: Receipts): Float = ReceiptList.getTotalDebt(rcps)
}

object ReceiptList {
  type isAppointment = Boolean
  type isExam = Boolean
  type isPrescription = Boolean
  type DateTime = Calendar
  type Cost = Option[Float]
  type isPaid = Boolean

  type Receipt = (Person, Person, isAppointment, isExam, isPrescription, DateTime, Cost, isPaid)
  type Receipts = List[Receipt]

  def getReceiptsAL(al: AppointmentList): Receipts = {
    @tailrec
    def loop(ap: Appointments, resp: Receipts): Receipts = {
      ap match {
        case Nil => resp
        case x :: tail => {
          val rcp = (x._1, x._3, true, false, false, x._2, x._4, x._5)
          loop(tail, rcp :: resp)
        }
      }
    }
    loop(al.appointments, List())
  }

//  def getReceiptsPAL(pal: PastAppointmentsList): Receipts = {
//    @tailrec
//    def loop(ap: Appointments, resp: Receipts): Receipts = {
//      ap match {
//        case Nil => resp
//        case x :: tail => {
//          val rcp = (x._1, x._3, true, false, false, x._2, x._4)
//          loop(tail, rcp :: resp)
//        }
//      }
//    }
//    loop(pal.appointments, List())
//  }

  def getReceiptsEL(el: ExamList): Receipts = {
    @tailrec
    def loop(exs: Exams, resp: Receipts): Receipts = {
      exs match {
        case Nil => resp
        case x::tail => {
          val rcp = (x._1, x._2, false, true, false, x._4, x._5, x._7)
          loop(tail, rcp :: resp)
        }
      }
    }
    loop(el.exams, List())
  }

//  def getReceiptsFEL(fel: FutureExamList): Receipts = {
//    @tailrec
//    def loop(exs: Exams, resp: Receipts): Receipts = {
//      exs match {
//        case Nil => resp
//        case x::tail => {
//          val rcp = (x._1, x._2, false, true, false, x._4, x._5)
//          loop(tail, rcp :: resp)
//        }
//      }
//    }
//    loop(fel.exams, List())
//  }

  def getReceiptsPL(pl: PrescriptionList): Receipts = {
    @tailrec
    def loop(prs: Prescriptions, resp: Receipts): Receipts = {
      prs match {
        case Nil => resp
        case x::tail => {
          val rcp = (x._1, x._2, false, false, true, x._3, x._5, x._6)
          loop(tail, rcp :: resp)
        }
      }
    }
    loop(pl.prescriptions, List())
  }

  def getAllReceipts(pal: PastAppointmentsList, pel: PastExamList, pl: PrescriptionList): Receipts = {
    getReceiptsAL(pal) ++ getReceiptsEL(pel) ++ getReceiptsPL(pl)
  }

  def getAllUnpaidReceipts(rcps: Receipts): Receipts = {
    rcps filter (x => x._8 == false)
  }

  def getAllPaidReceipts(rcps: Receipts): Receipts = {
    rcps filter (x => x._8 == true)
  }

  def getTotalDebt(rcps: Receipts): Float = {
    @tailrec
    def loop(rcps: Receipts, total: Float): Float = {
      rcps match {
        case Nil => total
        case x :: tail => {
          if (!x._8) {
            x._7 match {
              case None => loop(tail, total)
              case _ => loop(tail, total)
            }
          }
          else loop(tail, total)
        }
      }
    }
    loop (rcps, 0)
  }
}
