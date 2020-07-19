import java.time.{DayOfWeek, LocalDate, Month, Period}

import org.intellij.lang.annotations.JdkConstants.CalendarMonth

import collection.JavaConverters._

object ScalaFridayCounter {


  def printSortedFriday13(startYear: Int, endYear: Int): Unit ={

    val start =LocalDate.of(startYear,1,13)
    val end = LocalDate.of(endYear,1,13)

    val result = start.datesUntil(end, Period.ofMonths(1)).iterator().asScala.toStream
      .filter(x => x.getDayOfWeek == DayOfWeek.FRIDAY)
      .map(x => (x.getYear,1))
      .groupBy(x => x._1)
      .collect({ case (year, list) => (year,list.size)})
      .toList
      .sortBy(_._2)
      .reverse

    println(result)

  }

  def main(args: Array[String]): Unit = {

    this.printSortedFriday13(1926,2006)

  }

}
