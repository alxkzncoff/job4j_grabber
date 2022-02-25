package ru.job4j.ood.isp;

import java.time.LocalDate;

public class Book implements Item {

    @Override
    public int price() {
        return 400;
    }

    @Override
    public String condition() {
        return "new";
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
        throw new UnsupportedOperationException();
    }
}
