package class4hm1


import java.time._

import scala.collection.JavaConverters._
object ScalaFridayCounter {


  def printSortedFriday13(startYear: Int, endYear: Int): Unit ={

    val start =LocalDate.of(startYear,1,1)
    val end = LocalDate.of(endYear,1,1)

    val result = start.datesUntil(end).iterator().asScala.toStream
      .filter(x => x.getDayOfWeek == DayOfWeek.FRIDAY && x.getDayOfMonth==13)
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
