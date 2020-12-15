package com.hubertart.playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordCounter {

    private String sentence;

//    @Autowired
    private final WordCountConfig config;

    public WordCounter(WordCountConfig config){
        this.config = config;
    }

    public String getString() {
        return sentence;
    }

    public void setString(String sentence){
        this.sentence = sentence;
    }

    public String[] splitString() {
        return sentence.split(" ");
    }

//    public String stripSentence(){
//        return sentence.replaceAll("/[^a-zA-Z]/g", "");
//    }

    public Map<String, Integer> getCounts() {
        String[] words = sentence.split(" ");
        HashMap<String, Integer> counts = new HashMap<>();
        for(String word: words){
            boolean skipThis = false;
            if(!config.isCaseSensitive()){
                word = word.toLowerCase();
            }
            for(String skipWord : config.getSkipWords().getSkip()){
                if(skipWord.equals(word)){
                    skipThis = true;
                    break;
                }
            }
            if(!skipThis){
                word = word.replaceAll("[^a-zA-Z]+","");
                if(counts.containsKey(word)){
                    int count = counts.get(word);
                    count++;
                    counts.put(word, count);
                } else {
                    counts.put(word, 1);
                }
            }
        }
        return counts;
    }
}
