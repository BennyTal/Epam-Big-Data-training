package class4hm2.scala

import class4hm2.java.Seniority

case class Employee(salary: Int){

  def getSeniority():Seniority ={
    if (salary > 25000){
      Seniority.Sinior
    }else if(salary > 20000){
      Seniority.Middle
    }else{
      Seniority.Junior
    }
  }
}
