import org.reflections.Reflections
import org.reflections.scanners.SubTypesScanner
import org.reflections.util.{ClasspathHelper, ConfigurationBuilder}

case class CharacterFactory() {
  //Reflection API setup.
  val reflections: Reflections  = new Reflections(new ConfigurationBuilder()
    .setUrls(ClasspathHelper.forPackage("Characters"))
    .setScanners(new SubTypesScanner()))

  //Creating list of classes that implement 'Characters.Character' trait
  val classList:List[String] = reflections.getSubTypesOf(classOf[Characters.Character]).toArray().toList
    .toStream.map(_.toString)
    .filter(x => x.startsWith("class"))
    .toList

  def createCharacter(): Characters.Character = {

    //Creating random number
    val r = new scala.util.Random()
    val num = r.nextInt(classList.length)

    //Creating Constructor of a randomly-picked class at run time.
    val clsName = classList(num).split("class ")(1)
    val ctor = Class.forName(clsName).getConstructor()

    //Returning an Instance of the class we created dynamically.
    ctor.newInstance().asInstanceOf[Characters.Character]
  }
}
