package class3hm1

import scala.util.Random

trait Noble extends Character {

  val base:Int

  override def kick(c: Character): Unit = {
    val r = new scala.util.Random()
    val dmg:Int = base + r.nextInt(11)
    c.hp = c.hp - dmg
    commentating(c,dmg)
  }

}

