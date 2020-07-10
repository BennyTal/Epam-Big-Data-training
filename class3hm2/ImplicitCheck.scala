package class3hm2

object ImplicitCheck {


  def main(args: Array[String]): Unit = {
    import StringExt._
    val s1 = "Benny@email"
    println(s1 + " is email? " + (s1.isEmail))
    println("\"    \" is blank? " + ("    ".isBlank))
  }
}
