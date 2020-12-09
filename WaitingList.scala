import AppointmentList.{Appointment, Appointments, Client, DateTime, Doctor}
import scala.annotation.tailrec

case class WaitingList(override val medicalFacility: String, override val appointments: Appointments) extends AppointmentList(medicalFacility, appointments) {
  def addAppointment(ap: Appointment): Option[WaitingList] = WaitingList.addAppointment(this, ap)
  def nextAppointmentPatient(patient: Client): Option[Appointment] = WaitingList.nextAppointmentPatient(this, patient)
  def nextAppointmentPractitioner(doctor: Doctor): Option[Appointment] = WaitingList.nextAppointmentPractitioner(this, doctor)
  def availableSlot(dt: DateTime, doc:Doctor): Boolean = WaitingList.availableSlot(this, dt, doc)
  def sendSMS(dh: DateTime): Unit = WaitingList.sendSMS(this, dh)
  def toWaitingList(): WaitingList = WaitingList.toWaitingList(this)
  def changeAppointmentDate(ap: Appointment, date: DateTime): Option[WaitingList] = WaitingList.changeAppointmentDate(this, ap, date)
}

object WaitingList {
  
  type DateTime = Calendar

  // acrescentar Appointment
  def addAppointment(wl: WaitingList, ap: Appointment): Option[WaitingList] = {
    if (availableSlot(wl, ap._2, ap._3)) {
      Some(new WaitingList(wl.medicalFacility, wl.appointments :+ ap))
    } else {
      None
    }
  }

  def nextAppointmentPatient(wl: WaitingList, patient: Client): Option[Appointment] = {
    @tailrec
    def loop(aps: Appointments, p: Client): Option[Appointment] = {
      aps match {
        case Nil => None;
        case x::tail =>
          if(x._1.equals(p)) Some(x)
          else loop(tail, p)
      }
    }
    loop(wl.appointments, patient)
  }

  def changeAppointmentDate(wl: WaitingList, ap: Appointment, date: DateTime): Option[WaitingList] = {
    if(availableSlot(wl, date, ap._3) && wl.appointments.contains(ap)) {
      val nem_ap = (ap._1, date, ap._3, ap._4, false)
      val update = wl.appointments updated(wl.appointments indexOf ap, nem_ap)
      Some(new WaitingList(wl.medicalFacility, update))
    }
    else {
      None
    }
  }

  def nextAppointmentPractitioner(wl: WaitingList, doc: Doctor): Option[Appointment] = {
    @tailrec
    def loop(aps: Appointments, doc: Doctor): Option[Appointment] = {
      aps match {
        case Nil => None;
        case x::tail =>
          x._3
          if(x._3.equals(doc)) Some(x)
          else loop(tail, doc)
      }
    }
    loop(wl.appointments, doc)
  }

  //auxiliar
  def slotAvailable(ap: Appointment, dt: DateTime, doc:Doctor): Boolean = {
    if(ap._3 == doc && ap._2 == dt) false else true
  }

  // saber se aquela hora está disponivel
  def availableSlot(wl: WaitingList, dt: DateTime, doc:Doctor): Boolean = {
    (wl.appointments foldRight true)((e, b) => slotAvailable(e, dt, doc) && b)
  }

  // enviar sms na vespera da consulta
  def sendSMS(wl: WaitingList, dh: DateTime): Unit = {

  }

  def toWaitingList(al: AppointmentList): WaitingList = {
    new WaitingList(al.medicalFacility, al.appointments)
  }
}


