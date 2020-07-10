package class3hm1.Characters

class King extends class3hm1.Noble {

  val r = new scala.util.Random()
  override var power: Int = _
  override val base: Int = 5
  override var hp: Int = base + r.nextInt(11)
}
