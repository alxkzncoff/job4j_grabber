package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.storage.food.Food;
import ru.job4j.ood.lsp.storage.stategy.Context;
import ru.job4j.ood.lsp.storage.stategy.Storage;

import java.util.List;

/**
 * Пример реализации шаблона стратегия. Класс распределяет продукты
 * по хранилищам в зависимости от условий.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */

public class ControlQuality {

    public void put(List<Storage> storages, Food food) {
        Context context = new Context();
        for (Storage storage: storages) {
            context.setStorage(storage);
            context.execute(food);
        }
    }
}
