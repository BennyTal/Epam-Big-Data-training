package Characters

trait Noble extends Characters.Character {

  val base:Int

  override def kick(c: Characters.Character): Unit = {
    val r = new scala.util.Random()
    val dmg:Int = base + r.nextInt(11)
    c.hp = c.hp - dmg
    commentating(c,dmg)
  }

}
