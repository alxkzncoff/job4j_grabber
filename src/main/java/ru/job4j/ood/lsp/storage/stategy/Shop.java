package ru.job4j.ood.lsp.storage.stategy;

import ru.job4j.ood.lsp.storage.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {

    private final List<Food> storage = new ArrayList<>();

    @Override
    public void add(Food food) {
        if (checkQuality(food) >= 25 && checkQuality(food) <= 100) {
            if (checkQuality(food) > 75) {
                food.setPrice(food.getPrice() - (food.getPrice() * food.getDiscount()));
            }
            storage.add(food);
        }
    }

    @Override
    public List<Food> getStorage() {
        return List.copyOf(storage);
    }
}
