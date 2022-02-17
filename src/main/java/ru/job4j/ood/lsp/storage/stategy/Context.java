package ru.job4j.ood.lsp.storage.stategy;

import ru.job4j.ood.lsp.storage.food.Food;

public class Context {

    private Storage storage;

    public Context() {
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void execute(Food food) {
        storage.add(food);
    }
}
