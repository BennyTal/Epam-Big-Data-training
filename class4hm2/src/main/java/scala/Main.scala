package scala

import java.Seniority

import scala.collection.mutable.ListBuffer
import scala.util.Random

object Main {

  def initList():List[java.Employee] = {
    val rand:Random = Random
    var emp = new ListBuffer[java.Employee]()
    for ( i <- 1 to 10000){
      emp :+ Employee(6000 + rand.nextInt(50000))
    }
    emp.toList
  }

  //Exercise 1
  def seniorityMap(employees : List[java.Employee]):Map[Seniority,List[java.Employee]] = {

    employees.toStream
      .toList
      .groupBy(_.getSeniority())
  }

  //Exercise 2
  def seniorityCounter(employees : List[java.Employee]):Map[Seniority,Int] = {
    employees.toStream
      .map(x => (x.getSeniority,1))
      .toList
      .groupBy(_._1)
      .collect({case (a,b) => (a,b.size)})
  }

  //Exercise 3
  def seniorityMaxSalary(employees : List[java.Employee]):Map[Seniority,Integer] ={

    employees.toStream
      .sortBy(_.getSalary)
      .toList
      .groupBy(_.getSeniority())
      .collect({ case (seniority, employees) => (seniority,employees.last.getSalary)})
  }

  def main(args: Array[String]): Unit = {
    val employees = initList()
    println(seniorityMap(employees))
    println(seniorityCounter(employees))
    println(seniorityMaxSalary(employees))
  }
}
