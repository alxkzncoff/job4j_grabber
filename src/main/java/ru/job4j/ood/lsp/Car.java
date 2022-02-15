package ru.job4j.ood.lsp;

public class Car {

    protected double engine;
    protected String transmission;
    protected boolean airbags;
    protected boolean abs;

    public Car(double engine, String transmission, boolean airbags, boolean abs) {
        this.engine = engine;
        this.transmission = transmission;
        this.airbags = airbags;
        this.abs = abs;

    }

    public boolean safetyTest() {
        return airbags && abs;
    }
}
