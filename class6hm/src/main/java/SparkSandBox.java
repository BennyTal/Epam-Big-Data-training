import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.rdd.RDD;
import scala.Tuple2;

import java.util.List;
import java.util.stream.Collectors;

public class SparkSandBox {

    public static long lineCounter(JavaRDD<String> lines){
        return lines.count();
    }

    public static long BostonLongOrders(JavaRDD<String> lines){
        return lines
                .filter(s -> s.toLowerCase().contains("boston"))
                .map(s -> s.split(" ")[2])
                .filter(s -> Integer.parseInt(s) >= 10)
                .count();
    }

    public static Integer BostonTotalLength(JavaRDD<String> lines){
        return lines
                .filter(s -> s.toLowerCase().contains("boston"))
                .map(s -> Integer.parseInt(s.split(" ")[2]))
                .reduce(Integer::sum);
    }


    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("hello").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> taxiOrdersLines = sc.textFile("class6hm\\src\\main\\taxi_orders.txt");
        JavaRDD<String> driversLines = sc.textFile("class6hm\\src\\main\\drivers.txt");

//        System.out.println(lineCounter(taxiOrdersLines));
//        System.out.println(BostonLongOrders(taxiOrdersLines));
//        System.out.println(BostonTotalLength(taxiOrdersLines));


    }
}
