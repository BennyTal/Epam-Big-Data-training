package com.WordCounter.service;


import com.clearspring.analytics.util.Pair;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class WordCountService{

    @Autowired
    JavaSparkContext sc;

//    line -> line.split("\\s*[^a-zA-Z]+\\s*")

    public Map<String,Integer> getCount(String filename , int  top){

        List<String> garbageFile = sc.textFile("WordCounter/src/main/java/com/WordCounter/files/garbage_words.txt")
                .collect();
        Broadcast<List<String>> garbageWordsBroadcast = sc.broadcast(garbageFile);

        JavaRDD<String> text= sc.textFile("WordCounter/src/main/java/com/WordCounter/files/" + filename + ".txt");


            //From here on is the problem
            return (Map<String, Integer>) text.flatMap(line -> Arrays.asList(line.split("\\s*[^a-zA-Z]+\\s*")).iterator())
            .filter(word -> word.length() > 1 && garbageWordsBroadcast.value().contains(word))
            .map(word -> new Pair<String,Integer>(word,1))
            .groupBy(t -> t.left)
            .collect();
    }

}