import AppointmentList.{Appointment, Appointments, Bill, Client, DateTime, Doctor, Specialty}
import PersonList.{NIF, Name, Person}
import SpecialtyENUM.SpecialtyENUM

import scala.annotation.tailrec

class AppointmentList(val medicalFacility: String, val appointments: Appointments) extends Serializable {
  def specialityAppointmentList(s: Specialty): Appointments = AppointmentList.specialityAppointmentList(this, s)
  def organizeByName(): Appointments = AppointmentList.organizeByName(this)
  def findAppointment(nif: NIF, dh: DateTime, sp: Specialty): Option[Appointment] = AppointmentList.findAppointment(this, nif, dh, sp)
  //  def deleteAppointment(nif: NIF, dt: DateTime, sp: Specialty): AppointmentList = AppointmentList.deleteAppointment(this, nif, dt, sp)
  def filterAppointmentsDoc(doc: Doctor): Appointments = AppointmentList.filterAppointmentsDoc(this, doc)
  def filterAppointmentsDocDate(doc: Doctor, dt: DateTime): Appointments = AppointmentList.filterAppointmentsDocDate(this, doc, dt)
  def deleteAppointment(ap: Appointment): Option[AppointmentList] = AppointmentList.deleteAppointment(this, ap)
  def organizeList(): AppointmentList = AppointmentList.organizeList(this)
  def payDebt(ap: Appointment): Option[AppointmentList] = AppointmentList.payDebt(this, ap)
  def getAllAppointmentsDoc(doc: Doctor): Appointments = AppointmentList.getAllAppointmentsDoc(this, doc)
  def containsAppointment(ap: Appointment): Boolean = AppointmentList.containsAppointment(this, ap)
  def findAppointmentCombo(cli: Name, doc: Name, dt: DateTime): Option[Appointment] = AppointmentList.findAppointmentCombo(this, cli, doc, dt)
}

//trait AppointmentListType {
//  type Appointment = (Client, DateTime, Doctor, Bill)
//  type s = List[Appointment]
//
//  def specialityAppointmentList(al: AppointmentList, s: Specialty): Appointments
//  def addAppointment(al: AppointmentList, ap: Appointment): AppointmentList
//  def organizeList(al: AppointmentList): AppointmentList
//  def findAppointment(al: AppointmentList, nif: NIF, dh: DateTime, sp: Specialty): Option[Appointment]
//  def organizeByName(al: AppointmentList): Appointments
//  def deleteAppointment(al: AppointmentList, nif: NIF, dt: DateTime, sp: Specialty): AppointmentList
//  def deleteAppointment(al: AppointmentList, ap: Appointment): AppointmentList
//}

object AppointmentList /*extends AppointmentListType*/ {

  type DateTime = Calendar
  type Specialty = SpecialtyENUM
  type Bill = Option[Float]
  type Client = Person
  type Doctor = Person
  type isPaid = Boolean

  type Appointment = (Client, DateTime, Doctor, Bill, isPaid)
  type Appointments = List[Appointment]


  def specialityAppointmentList(al: AppointmentList, speciality: Specialty): Appointments = {
    @tailrec
    def loop (ap: Appointments, speciality: Specialty, lst: Appointments): Appointments = {
      ap match {
        case Nil => lst;
        case x :: tail => {
          if (x._3._5.equals(speciality)) loop(tail, speciality, lst ++ List(x))
          else loop(tail, speciality, lst)
        }
      }
    }
    loop(al.appointments, speciality, List())
  }

  def containsAppointment(al: AppointmentList, ap: Appointment): Boolean = {
    if(al.appointments.contains(ap)) true else false
  }

  def filterAppointmentsDoc(al: AppointmentList, doc: Doctor): Appointments = {
    al.appointments filter (x => x._3.equals(doc))
  }

  def filterAppointmentsDocDate(al: AppointmentList, doc: Doctor, dt: DateTime): Appointments = {
    al.appointments filter (x => x._3.equals(doc) && Calendar.sameDay(x._2, dt))
  }

  //auxiliar
  def slotAvailable(ap: Appointment, dt: DateTime, doc:Doctor): Boolean = {
    if(ap._3 == doc && ap._2 == dt) false else true
  }

  // saber se aquela hora está disponivel
  def availableSlot(al: AppointmentList, dt: DateTime, doc:Doctor): Boolean = {
    (al.appointments foldRight true)((e, b) => slotAvailable(e, dt, doc) && b)
  }

  def findAppointmentCombo(al: AppointmentList, cli: Name, doc: Name, dt: DateTime): Option[Appointment] = {
    al.appointments.find(x => x._1._1.equals(cli) && x._3._1.equals(doc) && x._2.equals(dt))
  }

  // confirmar NEW APPOINTMENT LIST ???
  //organizar a waiting list em função da data/hora
  def organizeList(al: AppointmentList): AppointmentList = {
    //    al.appointments.sortBy(_._2.getTime)
    val aptms = al.appointments.sortWith((a, b) => Calendar.isFirst(a._2, b._2))
    new AppointmentList(al.medicalFacility, aptms)
  }


  // confirmar NEW APPOINTMENT LIST ???
  def organizeByName(al: AppointmentList): Appointments = {
    al.appointments.sortWith(_._1._1 < _._1._1)
  }

  //o appointment devolvido pode ser Option??? ou pode devolver None?
  def findAppointment(al: AppointmentList, nif: NIF, dh: DateTime, sp: Specialty): Option[Appointment] = {
    val ap = al.appointments.filter(x => (x._2 == dh && x._1._3 == nif && x._3._5 == sp))
    if(ap.isEmpty) None /* DEVOLVER ERRO!!! */ else Some(ap.head)
  }

  def payDebt(al: AppointmentList, ap: Appointment): Option[AppointmentList] = {
    if (al.appointments.contains(ap) && !ap._5) {
      val paid = (ap._1, ap._2, ap._3, ap._4, true)
      val update = al.appointments updated(al.appointments indexOf ap, paid)
      Some(new AppointmentList(al.medicalFacility, update))
    }
    else {
      None
    }
  }

  // apagar Appointment
  // confirmar NEW APPOINTMENT LIST ???
  //  def deleteAppointment(al: AppointmentList, nif: NIF, dt: DateTime, sp: Specialty): AppointmentList = {
  //    @tailrec
  //    def loop(aptms: Appointments, nif: NIF, dt: DateTime, sp: Specialty, resp: Appointments): Appointments = {
  //      aptms match {
  //        case Nil => resp
  //        case x :: tail => {
  //          if (x._1._3 == nif && x._2 == dt && x._3._5 == sp) loop(tail, nif, dt, sp, resp) else loop(tail, nif, dt, sp, x :: resp)
  //        }
  //      }
  //    }
  ////    loop(al.appointments, nif, dt, sp, List())
  //    val aptms = loop(al.appointments, nif, dt, sp, List())
  //    new AppointmentList(al.medicalFacility, aptms)
  //  }


  // confirmar NEW APPOINTMENT LIST ???
  def deleteAppointment(al: AppointmentList, ap: Appointment): Option[AppointmentList] = {
    if(al.appointments.contains(ap)) {
      @tailrec
      def loop(aptms: Appointments, ap: Appointment, resp: Appointments): Appointments = {
        aptms match {
          case Nil => resp
          case x :: tail => {
            if (x == ap) loop(tail, ap, resp) else loop(tail, ap, x :: resp)
          }
        }
      }
      loop(al.appointments, ap, List())
      val aptms = loop(al.appointments, ap, List())
      val n = new AppointmentList(al.medicalFacility, aptms)
      Some(n)
    }
    else None
  }

  def getAllAppointmentsDoc(al: AppointmentList, doc: Doctor): Appointments = {
    al.appointments filter (x => x._3.equals(doc))
  }

}