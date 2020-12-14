package com.hubertart.playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/words")
public class WordCounterController {

    @Autowired
    WordCounter wordCounter;

    @PostMapping("/count")
    public Map<String, Integer> getCounts(@RequestBody String sentence){
        wordCounter.setString(sentence);
        return wordCounter.getCounts();
    }

}
