package scala

object Salary extends Enumeration {
  type Salary = Value

  val Senior: Salary.Value = Value(3)
  val Middle: Salary.Value = Value(2)
  val Junior: Salary.Value = Value(1)
}
