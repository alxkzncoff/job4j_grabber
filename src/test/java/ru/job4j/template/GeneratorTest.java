package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Map;

public class GeneratorTest {

    @Ignore @Test
    public void whenGenerated() {
        Generator generator = new GeneratorText();
        Map<String, String> valuesMap = Map.of("name", "Alex", "subject", "you");
        String template = "I am ${name}, Who are ${subject}?";
        String actual = generator.produce(template, valuesMap);
        String expected = "I am Alex, Who are you?";
        assertEquals(expected, actual);
    }

    @Ignore @Test(expected = IllegalArgumentException.class)
    public void wrongKeys() {
        Generator generator = new GeneratorText();
        Map<String, String> valuesMap = Map.of("surname", "Alex", "age", "you");
        String template = "I am ${name}, Who are ${subject}?";
        generator.produce(template, valuesMap);
    }

    @Ignore @Test(expected = IllegalArgumentException.class)
    public void wrongKeysNumber() {
        Generator generator = new GeneratorText();
        Map<String, String> valuesMap = Map.of("name", "Alex", "subject", "you", "age", "20");
        String template = "I am ${name}, Who are ${subject}?";
        generator.produce(template, valuesMap);
    }
}