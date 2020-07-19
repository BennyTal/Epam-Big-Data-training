
object GameManager {

  def fight(c1:Characters.Character, c2:Characters.Character):Unit = {
    while (c1.isAlive && c2.isAlive){
      c1.kick(c2)
      if(c2.isAlive)
        c2.kick(c1)
    }
  }

  def main(args: Array[String]): Unit = {
    val characterFactory = CharacterFactory()
    val c1 = characterFactory.createCharacter()
    println(c1.toString + " was created")
    val c2 = characterFactory.createCharacter()
    println(c2.toString + " was created")
    fight(c1,c2)
  }

}
