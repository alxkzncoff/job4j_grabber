package ru.job4j.ood.isp.menu.srp;

import java.nio.file.Path;
import java.util.List;

/**
 * Пример нарушения принципа SRP.
 * Интерфейс ищет данные.
 * @param <T>
 */

public interface Search<T> {

    List<String> find(Path path, String name);

    void output(List<T> items);
}
