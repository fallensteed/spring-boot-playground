package com.hubertart.playground;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {
    private String sentence;

    public String getString() {
        return sentence;
    }

    public void setString(String sentence){
        this.sentence = sentence;
    }

    public String[] splitString() {
        return sentence.split(" ");
    }

    public String stripSentence(){
        return sentence.replaceAll("/[^a-zA-Z]/g", "");
    }

    public Map<String, Integer> getCounts() {
        String[] words = sentence.split(" ");
        HashMap<String, Integer> counts = new HashMap<>();
        for(String word: words){

            word = word.replaceAll("[^a-zA-Z]+","");
            if(counts.containsKey(word)){
                int count = counts.get(word);
                count++;
                counts.put(word, count);
            } else {
                counts.put(word, 1);
            }
        }
        return counts;
    }
}
