package ru.job4j.kiss;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void intMax() {
        List<Integer> values = List.of(7, 2, 3, 0, 9, 5, 12);
        MaxMin findMax = new MaxMin();
        int actual = findMax.max(values, Integer::compareTo);
        int expected = 12;
        assertEquals(expected, actual);
    }

    @Test
    public void  stringMax() {
        List<String> values = List.of("apple", "orange", "peach", "strawberry");
        MaxMin findMax = new MaxMin();
        String actual = findMax.max(values, String::compareTo);
        String expected = "strawberry";
        assertEquals(expected, actual);
    }

    @Test
    public void intMin() {
        List<Integer> values = List.of(7, 2, 3, 0, 9, 5, 12);
        MaxMin findMax = new MaxMin();
        int actual = findMax.min(values, Integer::compareTo);
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void  stringMin() {
        List<String> values = List.of("apple", "orange", "peach", "strawberry");
        MaxMin findMax = new MaxMin();
        String actual = findMax.min(values, String::compareTo);
        String expected = "apple";
        assertEquals(expected, actual);
    }
}