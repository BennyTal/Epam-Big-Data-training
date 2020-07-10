package class3hm1.Characters

class Elf extends class3hm1.Character {
  override var power: Int = 10
  override var hp: Int = 10

  override def kick(c: class3hm1.Character): Unit = {
    if (c.power > this.power){
      c.power = c.hp - 1
      println(c.name + " didn't received any damage but his power was reduced by " + this.name)
    }else{
      commentating(c,c.hp)
      c.hp = 0
      println(c.name + " couldn't survive. \n" + this.name + " won!")
    }
  }

}
