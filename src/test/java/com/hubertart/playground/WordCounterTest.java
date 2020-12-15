package com.hubertart.playground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordCounterTest {

    WordCounter wordCounter;
    WordCountConfig config;

    @Test
    public void testInputStringIsReturned() {
        wordCounter = new WordCounter(config);
        wordCounter.setString("A brown cow jumps over a brown fox");
        String result = wordCounter.getString();
        assertEquals("A brown cow jumps over a brown fox", result);
    }

    @Test
    public void testSplitStringIntoComponents(){
        wordCounter = new WordCounter(config);
        wordCounter.setString("A brown cow jumps over a brown fox");
        String[] result = wordCounter.splitString();
        String[] expected = "A brown cow jumps over a brown fox".split(" ");
        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    public void testReturnsMapOfComponentsWithCount(){
        wordCounter = new WordCounter(config);
        wordCounter.setString("A brown cow jumps over a brown fox");
        Map<String, Integer> result = wordCounter.getCounts();
        HashMap<String, Integer> solution = new HashMap<>();
        solution.put("A", 1);
        solution.put("brown", 2);
        solution.put("cow", 1);
        solution.put("jumps", 1);
        solution.put("over", 1);
        solution.put("a", 1);
        solution.put("fox", 1);
        Map<String, Integer> expected = solution;
        assertEquals(expected, result);
    }

    @Test
    public void testReturnsMapWithoutAnyPunctuation(){
        wordCounter = new WordCounter(config);
        wordCounter.setString("this fox, it jumped this cow.");
        Map<String, Integer> result = wordCounter.getCounts();
        HashMap<String, Integer> solution = new HashMap<>();
        solution.put("this", 2);
        solution.put("fox", 1);
        solution.put("it", 1);
        solution.put("jumped", 1);
        solution.put("cow", 1);
        Map<String, Integer> expected = solution;
        assertEquals(expected, result);
    }

    @Test
    public void testCaseSensitiveFalseReturn(){
        wordCounter = new WordCounter(config);
        wordCounter.setString("The fox JUMPED cow");
        Map<String, Integer> result = wordCounter.getCounts();
        HashMap<String, Integer> solution = new HashMap<>();
        solution.put("the", 1);
        solution.put("fox", 1);
        solution.put("jumped", 1);
        solution.put("cow", 1);
        Map<String, Integer> expected = solution;
        assertEquals(expected, result);
    }
}
