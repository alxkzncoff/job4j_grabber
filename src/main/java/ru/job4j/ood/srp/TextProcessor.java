package ru.job4j.ood.srp;

/**
 * Пример нарушения принципа SRP.
 * Интерфейс обрабатывает текст.
 */

public interface TextProcessor {

    void process(String text);

    void save(String text);
}
