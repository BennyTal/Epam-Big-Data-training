import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object Main {


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

  case class Order(driverId:Int,city:String,length:Int)




  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("appname").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val taxiOrdersLines = sc.textFile("class6hm\\src\\main\\taxi_orders.txt")

    val orders: RDD[Order] = taxiOrdersLines.map{ s =>
      val values = s.split(" ")
      Order(values(0).toInt,values(1).toLowerCase(),values(2).toInt)
    }.persist(StorageLevel.MEMORY_AND_DISK)

    val driversLines = sc.textFile("class6hm\\src\\main\\drivers.txt")

    val bostonRDD: RDD[Order] = orders.filter(_.city == "boston").persist()

    println(bostonRDD.filter(_.length>10))
    println(bostonRDD.map(_.length).reduce(_+_))

//    bestDrivers(taxiOrdersLines,driversLines)


  }
}
