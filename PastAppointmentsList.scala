import AppointmentList.{Appointment, Appointments}

case class PastAppointmentsList(override val medicalFacility: String, override val appointments: Appointments) extends AppointmentList(medicalFacility, appointments) {
  def addAppointment(ap: Appointment, today: Calendar): Option[PastAppointmentsList] = PastAppointmentsList.addAppointment(this, ap, today)
  def toPastAppointmentList(): PastAppointmentsList = PastAppointmentsList.toPastAppointmentList(this)
}

object PastAppointmentsList {
  def validateInsertion(ap: Appointment, today: Calendar): Boolean = {
    if (!Calendar.isFirst(today, ap._2)) false else true
  }

  def addAppointment(pal: PastAppointmentsList, ap: Appointment, today: Calendar): Option[PastAppointmentsList] = {
    if (validateInsertion(ap, today)) {
      Some(new PastAppointmentsList(pal.medicalFacility, pal.appointments :+ ap))
    }
    else {
      None
    }
  }

  def toPastAppointmentList(al: AppointmentList): PastAppointmentsList = {
    new PastAppointmentsList(al.medicalFacility, al.appointments)
  }
}