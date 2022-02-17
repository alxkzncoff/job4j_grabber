package ru.job4j.ood.lsp.storage.stategy;

import ru.job4j.ood.lsp.storage.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {

    private final List<Food> storage = new ArrayList<>();

    @Override
    public void add(Food food) {
        if (checkQuality(food) > 100) {
            storage.add(food);
        }
    }

    @Override
    public List<Food> getStorage() {
        return List.copyOf(storage);
    }
}
