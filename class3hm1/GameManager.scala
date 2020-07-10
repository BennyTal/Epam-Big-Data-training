package class3hm1
import java.lang.module.Configuration
import java.lang.reflect.Constructor
import java.util.stream.Collectors

import scala.reflect.runtime.universe
import class3hm1.Characters.{Elf, Hobbit, King, Knight}
import org.reflections.Reflections
import org.reflections.scanners.{Scanner, SubTypesScanner}
import org.reflections.util.{ClasspathHelper, ConfigurationBuilder}


object GameManager {

  def fight(c1:Character, c2:Character):Unit = {
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
