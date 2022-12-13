package ru.job4j.ood.isp.menu.srp;

import java.util.Date;
import java.util.List;

/**
 * Пример нарушения принципа SRP.
 * Интерфейс сохраняет данные в БД.
 * @param <T>
 */

public interface ImportData<T> {

    List<T> create(List<Integer> ids, List<String> names, List<Date> dates);

    void importData(List<T> items);
}
