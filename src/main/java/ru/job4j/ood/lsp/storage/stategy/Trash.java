package ru.job4j.ood.lsp.storage.stategy;

import ru.job4j.ood.lsp.storage.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {

    private final List<Food> storage = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (checkQuality(food) > 100) {
            storage.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public List<Food> getStorage() {
        return List.copyOf(storage);
    }
}
