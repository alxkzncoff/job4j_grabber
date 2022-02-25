package ru.job4j.ood.isp;

import java.time.LocalDate;

public interface Item {

    int price();
    String condition();
    String material();
    String size();
    LocalDate expiredDate();
}
