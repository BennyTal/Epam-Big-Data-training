package class3hm1.Characters

case class Hobbit() extends class3hm1.Character {
  override var power: Int = 10
  override var hp: Int = 3

  override def kick(c: class3hm1.Character): Unit = toCry()

  def toCry(): Unit = println(this.name  + " is crying")
}
