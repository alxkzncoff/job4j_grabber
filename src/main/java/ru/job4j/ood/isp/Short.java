package ru.job4j.ood.isp;

import java.time.LocalDate;

public class Short implements Item {

    @Override
    public int price() {
        return 700;
    }

    @Override
    public String condition() {
        return "new";
    }

    @Override
    public String material() {
        return "cotton";
    }

    @Override
    public String size() {
        return "M";
    }

    @Override
    public LocalDate expiredDate() {
        throw new UnsupportedOperationException();
    }
}
