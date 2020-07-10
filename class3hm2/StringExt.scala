package class3hm2

object StringExt {
  implicit class StringExtension(string:String){
    def isBlank:Boolean=string.isEmpty || string.chars().allMatch(_ == " ".toInt)
    def isEmail:Boolean=string.contains("@")
  }
}
