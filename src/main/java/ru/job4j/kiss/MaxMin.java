package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс ищет минимальный и максимальный элемент списка.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */
public class MaxMin {

    public <T> T max(List<T> values, Comparator<T> comparator) {
        Predicate<Integer> max = p -> p < 0;
        return minMaxSearch(values, max, comparator);
    }

    public <T> T min(List<T> values, Comparator<T> comparator) {
        Predicate<Integer> min = p -> p > 0;
        return minMaxSearch(values, min, comparator);
    }

    private <T> T minMaxSearch(List<T> values, Predicate<Integer> predicate, Comparator<T> comparator) {
        T result = values.get(0);
        for (T value: values) {
            if (predicate.test(comparator.compare(result, value))) {
                result = value;
            }
        }
        return result;
    }
}
