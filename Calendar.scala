import MinuteENUM.MinuteENUM
import Calendar.{Day, Month, Year, Hour, Minute}
import Calendar.setDateTime

import java.time

case class Calendar (day: Int, month: Int, year: Int, hour : Int, minute: MinuteENUM){

  def setDateTime(day: Int, month: Int, year: Int, hour : Int, minute: MinuteENUM): Option[Calendar] = Calendar.setDateTime(day, month, year, hour, minute)
  def getCurrentTime(): Calendar = Calendar.getCurrentTime()
  def isFirst(cal1: Calendar, cal2: Calendar): Boolean = Calendar.isFirst(cal1, cal2)
}

object Calendar {
  type Day = Int
  type Month = Int
  type Year = Int
  type Hour = Int
  type Minute = MinuteENUM

  //	type Date = (Day, Month, Year, Hour, Minute)

  def getNrDays(month: Int, year: Int): Int = {
    month match {
      case 1 | 3 | 5 | 7 | 8 | 10 | 12 => 31;
      case 2 => if (year % 4 == 0){29} else {28};
      case 4 | 6 | 9 | 11 => 30;
    }
  }

  def setDateTime(day: Int, month: Int, year: Int, hour : Int, minute: MinuteENUM): Option[Calendar] = {
    val numberDays = getNrDays(month, year)
    if (day < 1 || numberDays < day || hour < 0 || hour > 23) None else {
      Some(new Calendar(day, month, year, hour, minute))
    }
  }

  def mostRecent(date1 : Calendar, date2 : Calendar): Calendar = { //not tested

    val year1 = date1.year
    val year2 = date2.year

    if (year1 < year2){
      //date2 is most recent
      return date2
    }

    if (year1 > year2){
      //date1 is most recent
      return date1
    } else {
      //Let's compare months
      val month1 = date1.month
      val month2 = date2.month

      if (month1 < month2){
        //date2 is most recent
        return date2
      }
      if (month1 > month2){
        //date1 is most recent
        return date1
      } else {
        //Let's compare days
        val day1 = date1.day
        val day2 = date2.day

        if (day1 < day2){
          //date2 is most recent
          return date2
        }

        if (day1 > day2){
          //date1 is most recent
          return date1
        } else {
          //Let's compare hours
          val hour1 = date1.hour
          val hour2 = date2.hour

          if (hour1 < hour2){
            //date2 is most recent
            return date2
          }

          if (hour1 > hour2){
            //date1 is most recent
            return date1
          } else {
            //Let's compare minutes
            val minute1 = date1.minute
            val minute2 = date2.minute

            if (minute1 < minute2){
              //date2 is most recent
              return date2
            }

            //	           if (minute1 >= minute2){
            //	             //date1 is most recent
            //	             date1
            //	           }

          }

        }

      }


    }
    date1



  }

  def getCurrentTime(): Calendar = { //closest getTime with minutes as an ENUM, not tested
    val now = java.time.LocalDateTime.now();
    val year = now.getYear();
    val month = now.getMonthValue();
    val day = now.getDayOfMonth();
    val hour = now.getHour();
    val minute = now.getMinute();
    val closestMinute = getClosestMinute(minute)
    Calendar(day, month, year, hour, closestMinute)
  }

  def getClosestMinute(mn: Int): MinuteENUM = {
    if (mn > 15 || mn < 45) MinuteENUM.Zero else MinuteENUM.Trinta
  }

  def getTime(date: Calendar): Calendar = {
    date
  }

  def isFirst(date1 : Calendar, date2 : Calendar): Boolean = {
    if (date1 == mostRecent(date1, date2)) true else false
  }

  def toString(date: Calendar): String = {
    if(date.minute == MinuteENUM.Zero){
      (date.day + "-" + date.month + "-" + date.year + " at " + date.hour + "h" + 0)
    } else {
      (date.day + "-" + date.month + "-" + date.year + " at " + date.hour + "h" + 30)
    }
  }


}