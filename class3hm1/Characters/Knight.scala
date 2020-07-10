package class3hm1.Characters

case class Knight() extends class3hm1.Noble {

  val r = new scala.util.Random()
  override var power: Int = _
  override val base: Int = 2
  override var hp: Int = base + r.nextInt(11)
}
