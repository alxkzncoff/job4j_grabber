package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.storage.food.Food;
import ru.job4j.ood.lsp.storage.stategy.Storage;

import java.util.List;

/**
 * Пример реализации шаблона стратегия. Класс распределяет продукты
 * по хранилищам в зависимости от условий.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */

public class ControlQuality {

    private final List<Storage> storages;

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    public void put(Food food) {
        for (Storage storage: storages) {
            if (storage.add(food)) {
                break;
            }
        }
    }
}
