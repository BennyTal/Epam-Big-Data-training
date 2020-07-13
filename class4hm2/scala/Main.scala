package class4hm2.scala

import class4hm2.java.Seniority

import scala.util.Random
import scala.collection.mutable.ListBuffer

object Main {

  def initList():List[Employee] = {
    val rand:Random = Random
    var emp = new ListBuffer[Employee]()
    for ( i <- 1 to 10000){
      emp += Employee(6000 + rand.nextInt(50000))
    }
    emp.toList
  }

  //Exercise 1
  def seniorityMap(employees : List[Employee]):Map[Seniority,List[Employee]] = {

    employees.toStream
      .toList
      .groupBy(_.getSeniority())
  }

  //Exercise 2
  def seniorityCounter(employees : List[Employee]):Map[Seniority,Int] = {
    employees.toStream
      .map(x => (x.getSeniority(),1))
      .toList
      .groupBy(_._1)
      .collect({case (a,b) => (a,b.size)})
  }

  //Exercise 3
  def seniorityMaxSalary(employees : List[Employee]):Map[Seniority,Int] ={

    employees.toStream
      .sortBy(_.salary)
      .toList
      .groupBy(_.getSeniority())
      .collect({ case (seniority, employees) => (seniority,employees.last.salary)})
  }

  def main(args: Array[String]): Unit = {
    val employees = initList()
    println(seniorityMap(employees))
    println(seniorityCounter(employees))
    println(seniorityMaxSalary(employees))
  }
}
