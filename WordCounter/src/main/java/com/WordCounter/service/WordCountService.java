package com.WordCounter.service;


import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class WordCountService{

    @Autowired
    JavaSparkContext sc;

//    line -> line.split("\\s*[^a-zA-Z]+\\s*")

    public Map<String, Long> getCount(String filename ,int top){

        List<String> garbageFile = sc.textFile("WordCounter/src/main/java/com/WordCounter/files/garbage_words.txt")
                .collect();
        Broadcast<List<String>> garbageWordsBroadcast = sc.broadcast(garbageFile);

        JavaRDD<String> text= sc.textFile("WordCounter/src/main/java/com/WordCounter/files/" + filename + ".txt");


        List<Tuple2<Long, String>> take = text
                .map(sentence -> sentence.replaceAll("[^a-zA-Z\\s]", "").toLowerCase())
                .flatMap(sentence -> Arrays.asList(sentence.split(" ")).iterator())
                .filter(word -> word.length() > 1 && !garbageWordsBroadcast.value().contains(word))
                .groupBy(t -> t)
                .mapValues(v -> v.spliterator().estimateSize())
                .map(Tuple2::swap)
                .sortBy(t -> t._1, false, 1)
                .take(top);

        return take.stream().collect(Collectors.toMap(Tuple2::_2, Tuple2::_1));
    }

}