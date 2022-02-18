package ru.job4j.ood.lsp.storage.stategy;

import ru.job4j.ood.lsp.storage.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {

    private final List<Food> storage = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (checkQuality(food) >= 25 && checkQuality(food) <= 100) {
            if (checkQuality(food) > 75) {
                food.setPrice(food.getPrice() - (food.getPrice() * food.getDiscount()));
            }
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
