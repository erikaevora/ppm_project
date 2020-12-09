import AppointmentList.Appointment
import ExamList.Exam
import PersonList.Person
import PrescriptionList.Prescription

//APENAS PARA TESTE!!!!!

object PopulateFacility {
  val today = Calendar.getCurrentTime()

  val cal1 = Calendar.setDateTime(29, 2, 2000, 20, MinuteENUM.Trinta).get
  val cal2 = Calendar.setDateTime(12, 5, 2001, 9, MinuteENUM.Trinta).get
  val cal3 = Calendar.setDateTime(2, 12, 2001, 8, MinuteENUM.Zero).get
  val cal4 = Calendar.setDateTime(10, 6, 1992, 8, MinuteENUM.Zero).get
  val cal5 = Calendar.setDateTime(28, 8, 1992, 8, MinuteENUM.Zero).get
  val cal6 = Calendar.setDateTime(20, 2, 1992, 8, MinuteENUM.Zero).get
  val cal7 = Calendar.setDateTime(13, 2, 1987, 8, MinuteENUM.Zero).get
  val cal8 = Calendar.setDateTime(10, 10, 1986, 8, MinuteENUM.Zero).get
  val cal9 = Calendar.setDateTime(2, 12, 2003, 8, MinuteENUM.Zero).get
  val cal10 = Calendar.setDateTime(5, 7, 2010, 8, MinuteENUM.Zero).get
  val cal11 = Calendar.setDateTime(14, 12, 1999, 8, MinuteENUM.Zero).get
  val cal12 = Calendar.setDateTime(15, 1, 2001, 8, MinuteENUM.Zero).get
  val cal13 = Calendar.setDateTime(16, 2, 1998, 8, MinuteENUM.Zero).get
  val cal14 = Calendar.setDateTime(17, 3, 1997, 8, MinuteENUM.Zero).get
  val cal15 = Calendar.setDateTime(18, 4, 1996, 8, MinuteENUM.Zero).get

  val hoje = Calendar.setDateTime(20, 11, 2020, 20, MinuteENUM.Trinta).get
  val amanha = Calendar.setDateTime(21, 11, 2020, 9, MinuteENUM.Trinta).get
  val depoisAmanha = Calendar.setDateTime(22, 11, 2020, 9, MinuteENUM.Trinta).get
  val natal = Calendar.setDateTime(25, 12, 2020, 8, MinuteENUM.Zero).get
  val anonovo = Calendar.setDateTime(31, 12, 2020, 0, MinuteENUM.Zero).get

  val vinteCincodAbril = Calendar.setDateTime(25, 4, 1975, 0, MinuteENUM.Zero).get
  val vinteCincodAbril2 = Calendar.setDateTime(26, 4, 1975, 0, MinuteENUM.Zero).get
  val vinteCincodAbril3 = Calendar.setDateTime(27, 4, 1975, 0, MinuteENUM.Zero).get

  val name1 = "CC Rio de Mouro"

  val doc1: Person = ("Joao", cal1, "123456789", Some("969696969"), Some(SpecialtyENUM.GP), None, "password", false)
  val doc2: Person = ("Susana", cal2, "123456790", Some("000111222"), Some(SpecialtyENUM.Cardiac), None, "password", false)
  val doc3: Person = ("Antonio", cal3, "123456791", Some("333444555"), Some(SpecialtyENUM.Dermatology), None, "password", false)
  val doc4: Person = ("Dorotheia", cal4, "123456792", Some("666777888"), Some(SpecialtyENUM.Gastroenterology), None, "password", false)
  val doc5: Person = ("Mario David", cal5, "123456793", Some("999000111"), Some(SpecialtyENUM.Ophthalmology), None, "password", false)

  val cli1: Person = ("Erica", cal6, "555111666", Some("558822"), None, Some(doc1._3), "password", true)
  val cli2: Person = ("Maria", cal7, "777555999", Some("228855"), None, Some(doc1._3), "password", true)
  val cli3: Person = ("Antonio", cal8, "111555333", Some("555888222"), None, Some(doc1._3), "password", true)
  val cli4: Person = ("Carlos", cal9, "111444777", Some("777555333"), None, None, "password", true)
  val cli5: Person = ("Andre", cal10, "777444111", Some("999555111"), None, None, "password", true)
  val cli6: Person = ("Francisco", cal11, "888555222", Some("555999111"), None, None, "password", true)
  val cli7: Person = ("Rui", cal12, "999666333", Some("111999555"), None, None, "password", true)
  val cli8: Person = ("Ana", cal13, "111222333", Some("444666777"), None, None, "password", true)
  val cli9: Person = ("Maria", cal14, "444555666", Some("222444777"), None, None, "password", true)
  val cli10: Person = ("Joana", cal15, "777888999", Some("999555888"), None, None, "password", true)

  val aptm1: Appointment = (cli1, hoje, doc1, None, false)
  val aptm2: Appointment = (cli2, hoje, doc2, None, false)
  val aptm3: Appointment = (cli3, amanha, doc3, None, false)
  val aptm4: Appointment = (cli4, amanha, doc2, None, false)
  val aptm5: Appointment = (cli5, depoisAmanha, doc2, None, false)
  val aptm6: Appointment = (cli6, depoisAmanha, doc3, None, false)
  val aptm7: Appointment = (cli7, natal, doc2, None, false)
  val aptm8: Appointment = (cli8, natal, doc4, None, false)
  val aptm9: Appointment = (cli9, anonovo, doc5, None, false)
  val aptm10: Appointment = (cli10, anonovo, doc2, None, false)

  val aptm11: Appointment = (cli5, vinteCincodAbril, doc2, None, false)
  val aptm12: Appointment = (cli5, vinteCincodAbril2, doc2, None, false)
  val aptm13: Appointment = (cli5, vinteCincodAbril3, doc2, None, false)
  val aptm14: Appointment = (cli1, vinteCincodAbril, doc3, None, false)
  val aptm15: Appointment = (cli2, vinteCincodAbril2, doc3, None, false)

  val aptm16: Appointment = (cli3, hoje, doc3, None, false)
  val aptm17: Appointment = (cli4, natal, doc3, None, false)
  val aptm18: Appointment = (cli5, anonovo, doc3, None, false)
  val aptm19: Appointment = (cli6, amanha, doc1, None, false)
  val aptm20: Appointment = (cli7, depoisAmanha, doc1, None, false)

  val exam1: Exam = (cli3, doc3, SpecialtyENUM.Cardiac, natal, Some(0), Some("Cardiopatia"), false)
  val exam2: Exam = (cli2, doc2, SpecialtyENUM.GP, anonovo, Some(20), Some("Escrobuto"), false)
  val exam3: Exam = (cli1, doc2, SpecialtyENUM.Pulmonology, amanha, Some(85), Some("covid-19"), false)
  val exam4: Exam = (cli5, doc2, SpecialtyENUM.Neurology, depoisAmanha, Some(20), Some("Inconclusivo"), false)
  val exam5: Exam = (cli5, doc2, SpecialtyENUM.Dermatology, hoje, Some(0), None, false)

  val exam6: Exam = (cli6, doc1, SpecialtyENUM.Dermatology, hoje, Some(0), None, false)
  val exam7: Exam = (cli7, doc3, SpecialtyENUM.Anaesthesia, hoje, Some(0), None, false)
  val exam8: Exam = (cli8, doc5, SpecialtyENUM.Dermatology, hoje, Some(0), None, false)
  val exam9: Exam = (cli9, doc4, SpecialtyENUM.GP, hoje, Some(0), None, false)
  val exam10: Exam = (cli10, doc4, SpecialtyENUM.Gastroenterology, amanha, Some(0), None, false)

  val presc1: Prescription = (cli1, doc1, hoje, "Ebastina", Some(35), false)
  val presc2: Prescription = (cli2, doc3, hoje, "Abavil", Some(45), false)
  val presc3: Prescription = (cli2, doc3, hoje, "Acarbose J. Neves", Some(55), false)
  val presc4: Prescription = (cli5, doc3, hoje, "Aceclofenan Mylan", None, false)
  val presc5: Prescription = (cli5, doc3, hoje, "Acetar", Some(0), false)

  val presc6: Prescription = (cli6, doc3, hoje, "Gasviscon", Some(0), false)
  val presc7: Prescription = (cli7, doc3, hoje, "Ben-U-Ron", Some(0), false)
  val presc8: Prescription = (cli8, doc3, hoje, "Brufen", Some(0), false)
  val presc9: Prescription = (cli9, doc3, hoje, "Brufen", Some(0), false)
  val presc10: Prescription = (cli10, doc3, hoje, "Brufen", Some(0), false)

  var wtl: WaitingList = new WaitingList(name1, List())
  var pal: PastAppointmentsList = new PastAppointmentsList(name1, List())
  var docl: PersonList = new PersonList(name1, List())
  var patl: PersonList = new PersonList(name1, List())
  var prescl: PrescriptionList = new PrescriptionList(name1, List())
  var fexl: FutureExamList = new FutureExamList(name1, List())
  var pexl: PastExamList = new PastExamList(name1, List())

  def getMedicalFacility1(): MedicalFacility = {
    wtl = wtl.addAppointment(aptm1).get
    wtl = wtl.addAppointment(aptm2).get
    wtl = wtl.addAppointment(aptm3).get
    wtl = wtl.addAppointment(aptm4).get
    wtl = wtl.addAppointment(aptm5).get
    wtl = wtl.addAppointment(aptm6).get
    wtl = wtl.addAppointment(aptm7).get
    wtl = wtl.addAppointment(aptm8).get
    wtl = wtl.addAppointment(aptm9).get
    wtl = wtl.addAppointment(aptm10).get
    wtl = wtl.addAppointment(aptm16).get
    wtl = wtl.addAppointment(aptm17).get
    wtl = wtl.addAppointment(aptm18).get
    wtl = wtl.addAppointment(aptm19).get
    wtl = wtl.addAppointment(aptm20).get

    pal = pal.addAppointment(aptm11, today).get
    pal = pal.addAppointment(aptm12, today).get
    pal = pal.addAppointment(aptm13, today).get
    pal = pal.addAppointment(aptm14, today).get
    pal = pal.addAppointment(aptm15, today).get

    docl = docl.addPerson(doc1).get
    docl = docl.addPerson(doc2).get
    docl = docl.addPerson(doc3).get
    docl = docl.addPerson(doc4).get
    docl = docl.addPerson(doc5).get

    patl = patl.addPerson(cli1).get
    patl = patl.addPerson(cli2).get
    patl = patl.addPerson(cli3).get
    patl = patl.addPerson(cli4).get
    patl = patl.addPerson(cli5).get
    patl = patl.addPerson(cli6).get
    patl = patl.addPerson(cli7).get
    patl = patl.addPerson(cli8).get
    patl = patl.addPerson(cli9).get
    patl = patl.addPerson(cli10).get

    prescl = prescl.addPrescription(presc1)
    prescl = prescl.addPrescription(presc2)
    prescl = prescl.addPrescription(presc3)
    prescl = prescl.addPrescription(presc4)
    prescl = prescl.addPrescription(presc5)
    prescl = prescl.addPrescription(presc6)
    prescl = prescl.addPrescription(presc7)
    prescl = prescl.addPrescription(presc8)
    prescl = prescl.addPrescription(presc9)
    prescl = prescl.addPrescription(presc10)

    fexl = fexl.addExam(exam1)
    fexl = fexl.addExam(exam2)
    fexl = fexl.addExam(exam5)
    fexl = fexl.addExam(exam6)
    fexl = fexl.addExam(exam7)
    fexl = fexl.addExam(exam8)
    fexl = fexl.addExam(exam9)
    fexl = fexl.addExam(exam10)

//    pexl = pexl.addExam(exam1, today).get
//    pexl = pexl.addExam(exam2, today).get

    var mf1: MedicalFacility = new MedicalFacility(name1, wtl, pal, docl, patl, prescl, fexl, pexl)

    mf1
  }

  def getMedicalFacility2(): MedicalFacility = {
    val name2 = "CC Mem Martins"

    var wtl2: WaitingList = new WaitingList(name2, List())
    var pal2: PastAppointmentsList = new PastAppointmentsList(name2, List())
    var docl2: PersonList = new PersonList(name2, List())
    var patl2: PersonList = new PersonList(name2, List())
    var prescl2: PrescriptionList = new PrescriptionList(name2, List())
    var fexl2: FutureExamList = new FutureExamList(name2, List())
    var pexl2: PastExamList = new PastExamList(name2, List())

    var mf2: MedicalFacility = new MedicalFacility(name2, wtl2, pal2, docl2, patl2, prescl2, fexl2, pexl2)

    mf2
  }
}
