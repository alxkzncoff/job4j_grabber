package ru.job4j.ood.lsp.storage;

import org.junit.Test;
import ru.job4j.ood.lsp.storage.food.Bread;
import ru.job4j.ood.lsp.storage.food.Food;
import ru.job4j.ood.lsp.storage.food.Meat;
import ru.job4j.ood.lsp.storage.food.Milk;
import ru.job4j.ood.lsp.storage.stategy.Shop;
import ru.job4j.ood.lsp.storage.stategy.Storage;
import ru.job4j.ood.lsp.storage.stategy.Trash;
import ru.job4j.ood.lsp.storage.stategy.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void whenMeatWarehouse() {
        ControlQuality controlQuality = new ControlQuality();
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<Storage> storages = new ArrayList<>();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        Food meat = new Meat("pork",
                LocalDate.of(2022, 3, 15),
                LocalDate.of(2022, 2, 15),
                150.0,
                0.25);
        controlQuality.put(storages, meat);
        assertEquals(meat, warehouse.getStorage().get(0));
    }

    @Test
    public void whenMilkShop() {
        ControlQuality controlQuality = new ControlQuality();
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<Storage> storages = new ArrayList<>();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        Food milk = new Milk("milk",
                LocalDate.of(2022, 2, 24),
                LocalDate.of(2022, 2, 10),
                80.0,
                0.4);
        controlQuality.put(storages, milk);
        assertEquals(milk, shop.getStorage().get(0));
    }

    @Test
    public void whenBreadShopDiscount() {
        ControlQuality controlQuality = new ControlQuality();
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<Storage> storages = new ArrayList<>();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        Food bread = new Bread("bread",
                LocalDate.of(2022, 2, 18),
                LocalDate.of(2022, 2, 10),
                40.0,
                0.5);
        Food expected = new Bread("bread",
                LocalDate.of(2022, 2, 18),
                LocalDate.of(2022, 2, 10),
                20.0,
                0.5);
        controlQuality.put(storages, bread);
        assertEquals(expected, shop.getStorage().get(0));
    }

    @Test
    public void whenBeefTrash() {
        ControlQuality controlQuality = new ControlQuality();
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<Storage> storages = new ArrayList<>();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        Food beef = new Meat("beef",
                LocalDate.of(2022, 2, 16),
                LocalDate.of(2022, 2, 1),
                200.0,
                0.4);
        controlQuality.put(storages, beef);
        assertEquals(beef, trash.getStorage().get(0));
    }

}