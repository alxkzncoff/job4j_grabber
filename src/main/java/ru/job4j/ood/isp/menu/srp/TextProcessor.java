package ru.job4j.ood.isp.menu.srp;

/**
 * Пример нарушения принципа SRP.
 * Интерфейс обрабатывает текст.
 */

public interface TextProcessor {

    void process(String text);

    void save(String text);
}
