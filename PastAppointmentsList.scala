import AppointmentList.{Appointment, Appointments}

case class PastAppointmentsList(override val medicalFacility: String, override val appointments: Appointments) extends AppointmentList(medicalFacility, appointments) {
  def addAppointment(ap: Appointment, today: Calendar): Option[PastAppointmentsList] = PastAppointmentsList.addAppointment(this, ap, today)
  def toPastAppointmentList(): PastAppointmentsList = PastAppointmentsList.toPastAppointmentList(this)
}

object PastAppointmentsList {
  def addAppointment(pal: PastAppointmentsList, ap: Appointment, today: Calendar): Option[PastAppointmentsList] = {
    if (validateInsertion(ap, today)) {
      Some(new PastAppointmentsList(pal.medicalFacility, pal.appointments :+ ap))
    }
    else {
      None
    }
  }

  def validateInsertion(ap: Appointment, today: Calendar): Boolean = {
    if (Calendar.isFirst(ap._2, today)) true else false
  }

  def toPastAppointmentList(al: AppointmentList): PastAppointmentsList = {
    new PastAppointmentsList(al.medicalFacility, al.appointments)
  }
}