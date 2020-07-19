package Characters


case class Hobbit() extends Character {
  override var power: Int = 10
  override var hp: Int = 3

  override def kick(c: Character): Unit = toCry()

  def toCry(): Unit = println(this.name  + " is crying")
}
