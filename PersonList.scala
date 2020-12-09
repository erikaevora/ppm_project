import PersonList.{NIF, People, Person}
import SpecialtyENUM.SpecialtyENUM

import scala.annotation.tailrec

case class PersonList(medicalFacility: String, people: People) extends Serializable{

  def searchPerson(n: String): Option[Person] = PersonList.searchPerson(this.people, n)
  def addPerson(p: Person): Option[PersonList] = PersonList.addPerson(this, p)
  def deletePerson(p: Person): PersonList = PersonList.deletePerson(this, p)
  def changePhoneNr(p: Person, n: String): Option[PersonList] = PersonList.changePhoneNr(this, p, n)
  def searchPersonNIF(nif: NIF): Option[Person] = PersonList.searchPersonNIF(this, nif)
  def containsPerson(p: Person): Boolean = PersonList.containsPerson(this, p)
}

object PersonList {
  type Name = String
  type BirthDate = Calendar
  type NIF = String
  type PhoneNr = Option[String]
  type Password = String
  type IsPatient = Boolean
  type Specialty = Option[SpecialtyENUM]
  type GP = Option[NIF]

  type Person = (Name, BirthDate, NIF, PhoneNr, Specialty, GP, Password, IsPatient)
  type People = List[Person]

  //procura um practitioner pelo seu NIF
  @tailrec
  def searchPerson(l: People, n: String): Option[Person] = {
    l match {
      case Nil => None
      case x::xs => if(x._3 == n) Some(x) else searchPerson(xs, n)
    }
  }

  def searchPersonNIF(pl: PersonList, nif: NIF): Option[Person] = {
    pl.people match {
      case Nil => None
      case x::xs => if(x._3.equals(nif)) Some(x) else searchPerson(xs, nif)
    }
  }

  def isDoctor(p: Person): Boolean = !p._8

  def addPerson(pl: PersonList, p: Person): Option[PersonList] = {
    val person = searchPersonNIF(pl, p._3)  //variavel para verificar se ja existe alguem com o NIF de p

    if(pl.people.contains(p) || person.isDefined)
      None
    else {
      Some(PersonList(pl.medicalFacility, pl.people :+ p))
    }
  }

  def deletePerson(pl: PersonList, p: Person): PersonList = {
    if(pl.people contains p)
      new PersonList(pl.medicalFacility, pl.people filter (x => !x.equals(p)))
    else
      pl
  }

  def containsPerson(pl: PersonList, p: Person): Boolean = {
    if(pl.people.contains(p)) true else false
  }

  def changePhoneNr(pl: PersonList, p: Person, n: String): Option[PersonList] = {
    if(pl.people.contains(p)) {
      val p_updated = (p._1, p._2, p._3, Some(n), p._5, p._6, p._7, p._8)
      val nl = PersonList(pl.medicalFacility, pl.people updated(pl.people.indexOf(p), p_updated))
      Some(nl)
    }
    else {
      None
    }
  }
}