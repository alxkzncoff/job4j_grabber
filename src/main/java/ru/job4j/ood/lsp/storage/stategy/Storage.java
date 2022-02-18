package ru.job4j.ood.lsp.storage.stategy;

import ru.job4j.ood.lsp.storage.food.Food;

import java.time.LocalDate;
import java.util.List;

public interface Storage {

    default int checkQuality(Food food) {
        return (LocalDate.now().getDayOfYear() - food.getCreateDate().getDayOfYear()) * 100
                / (food.getExpiryDate().getDayOfYear() - food.getCreateDate().getDayOfYear());
    }

    boolean add(Food food);

    List<Food> getStorage();
}
