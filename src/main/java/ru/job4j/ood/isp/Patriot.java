package ru.job4j.ood.isp;

public class Patriot implements Car {

    @Override
    public void drive() {
        System.out.println("Move forward.");
    }

    @Override
    public void refuel() {
        System.out.println("Car refueled!");
    }

    @Override
    public void beep() {
        System.out.println("Beep!");
    }

    @Override
    public void offRoad() {
        System.out.println("4WD enabled.");
    }
}
