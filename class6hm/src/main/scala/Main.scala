import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Main {

  def lineCounter(lines: RDD[String]): Long ={
    lines.count()
  }

  def bostonLongOrders(lines: RDD[String]): Long ={ //To fix
    lines.filter(s => s.toLowerCase.contains("boston") &&
      s.split(" ")(2).toInt >= 10).count()
  }

  def bostonTotalLength(lines: RDD[String]): Double ={
    lines
      .filter(_.toLowerCase().contains("boston"))
      .map(s => s.split(" ")(2).toDouble)
      .sum
  }

  def bestDrivers(ordersLines: RDD[String], driversLines: RDD[String]): Unit ={

    val bestDriversId = ordersLines
      .map(s => (s.split(" ")(0),s.split(" ")(2).toDouble))
      .groupBy(_._1)
      .mapValues(_.map(_._2).sum)
      .sortBy(- _._2)
      .take(3).map(_._1)

    driversLines
      .filter(s => bestDriversId.contains(s.substring(0,3))).foreach(println)


  }




  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("appname").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val taxiOrdersLines = sc.textFile("class6hm\\src\\main\\taxi_orders.txt")
    val driversLines = sc.textFile("class6hm\\src\\main\\drivers.txt")

//    println(lineCounter(taxiOrdersLines))
//    println(lineCounter(driversLines))

//    println("Boston long orders: " + bostonLongOrders(taxiOrdersLines))
//    println("Totabl km Boston: " + bostonTotalLength(taxiOrdersLines))
//    bestDrivers(taxiOrdersLines,driversLines)


  }
}
