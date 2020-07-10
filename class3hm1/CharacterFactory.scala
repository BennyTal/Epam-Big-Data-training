package class3hm1

import org.reflections.Reflections
import org.reflections.scanners.SubTypesScanner
import org.reflections.util.{ClasspathHelper, ConfigurationBuilder}

case class CharacterFactory() {
  //Reflection API setup.
  val reflections: Reflections  = new Reflections(new ConfigurationBuilder()
    .setUrls(ClasspathHelper.forPackage("class3hm1/Characters"))
    .setScanners(new SubTypesScanner()))

  //Creating list of classes that implement 'Character' trait
  val classList:List[AnyRef] = reflections.getSubTypesOf(classOf[Character]).toArray().toList
    .toStream
    .filter(x => x.toString.startsWith("class"))
    .toList

  def createCharacter(): Character = {

    //Creating random number
    val r = new scala.util.Random()
    val num = r.nextInt(classList.length)

    //Creating Constructor of a randomly-picked class at run time.
    val clsName = classList(num).toString.split("class ")(1)
    val ctor = Class.forName(clsName).getConstructor()

    //Returning an Instance of the class we created dynamically.
    ctor.newInstance().asInstanceOf[Character]
  }
}

/*
val ru = scala.reflect.runtime.universe
    val m = ru.runtimeMirror(getClass.getClassLoader)

    val ctorC = ru.typeOf[Characters.Hobbit].decl(ru.termNames.CONSTRUCTOR).asMethod
    val hobbitClsSmbl = ru.typeOf[Hobbit].typeSymbol.asClass
    val hobbitMirror = m.reflectClass(hobbitClsSmbl)
    val hobbitM = m.reflect(new Hobbit)
    val ctorM = hobbitMirror.reflectConstructor(ctorC)
 */
