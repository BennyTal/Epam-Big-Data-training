

import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SparkSession, functions}

object SandBox {


  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder
      .appName("DataFramesSandBox")
      .master("local[*]")
      .config("spark.sql.warehouse.dir", "target/spark-warehouse")
      .getOrCreate


    val tableRow:DataFrame = sparkSession.read.json("class9 - DataFrame/src/main/data/profiles")
    tableRow.show()

    val dataTypes = tableRow.schema.fields.map(f => f.dataType)

    println("table data types: ")
    dataTypes.foreach(println)

    val salaryTable = tableRow.withColumn("salary",
      col("age")
        .multiply(
          when(col("age") > 30, col("age"))
            .otherwise(5)))

    salaryTable.show()

    val popularTech = tableRow
      .select(explode(col("keywords")))
      .groupBy("col")
      .count
      .orderBy(desc("count"))
      .take(1)
      .toList.head.get(0)

    salaryTable.filter(col("salary").leq(1200) &&
      array_contains(col("keywords"),popularTech))
      .show()


  }

}
