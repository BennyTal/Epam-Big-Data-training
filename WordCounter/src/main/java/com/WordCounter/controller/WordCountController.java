package com.WordCounter.controller;

import com.WordCounter.service.WordCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class WordCountController {

    @Autowired
    WordCountService service;

    @RequestMapping(method = RequestMethod.POST, path = "/topX")
    public Map<String, Integer> count(
            @RequestParam(required = false, defaultValue = "5") int top,
            @RequestParam(required = false, defaultValue = "text") String filename) {

        return service.getCount(filename, top);
    }
}