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
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<Storage> storages = new ArrayList<>();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        ControlQuality controlQuality = new ControlQuality(storages);
        Food meat = new Meat("pork",
                LocalDate.now().plusDays(28),
                LocalDate.now().minusDays(2),
                150.0,
                0.25);
        controlQuality.put(meat);
        assertEquals(meat, warehouse.getStorage().get(0));
    }

    @Test
    public void whenMilkShop() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<Storage> storages = new ArrayList<>();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        ControlQuality controlQuality = new ControlQuality(storages);
        Food milk = new Milk("milk",
                LocalDate.now().plusDays(7),
                LocalDate.now().minusDays(7),
                80.0,
                0.4);
        controlQuality.put(milk);
        assertEquals(milk, shop.getStorage().get(0));
    }

    @Test
    public void whenBreadShopDiscount() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<Storage> storages = new ArrayList<>();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        ControlQuality controlQuality = new ControlQuality(storages);
        Food bread = new Bread("bread",
                LocalDate.now().plusDays(1),
                LocalDate.now().minusDays(7),
                40.0,
                0.5);
        Food expected = new Bread("bread",
                LocalDate.now().plusDays(1),
                LocalDate.now().minusDays(7),
                20.0,
                0.5);
        controlQuality.put(bread);
        assertEquals(expected, shop.getStorage().get(0));
    }

    @Test
    public void whenBeefTrash() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<Storage> storages = new ArrayList<>();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        ControlQuality controlQuality = new ControlQuality(storages);
        Food beef = new Meat("beef",
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(16),
                200.0,
                0.4);
        controlQuality.put(beef);
        assertEquals(beef, trash.getStorage().get(0));
    }

}