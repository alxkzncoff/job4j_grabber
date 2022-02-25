package ru.job4j.ood.isp;

import java.time.LocalDate;

public class Cheese implements Item {

    @Override
    public int price() {
        return 200;
    }

    @Override
    public String condition() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String material() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public LocalDate expiredDate() {
        return LocalDate.now().plusDays(10);
    }
}
