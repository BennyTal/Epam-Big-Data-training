package class3hm1
import com.github.javafaker.Faker


trait Character {

  val name:String = {
    val faker = new Faker
    faker.name.fullName+ "(" + getClass.getName.split('.')(2) + ")"
  }
  var power:Int
  var hp: Int

  def kick(c:Character):Unit
  def isAlive:Boolean = hp > 0

  override def toString: String = {
    this.name
  }

  def commentating(c: Character, dmg: Int): Unit = {
    if (c.isAlive) {
      println(c.name + " received " + dmg + " from " + this.name)
    } else {
      println(c.name + " received " + dmg + " and couldn't survive. \n" + this.name + " won!")
    }
  }
}
