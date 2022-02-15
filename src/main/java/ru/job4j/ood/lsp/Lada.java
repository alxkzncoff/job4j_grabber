package ru.job4j.ood.lsp;

public class Lada extends Car {

    public Lada(double engine, String transmission, boolean airbags, boolean abs) {
        super(engine, transmission, airbags, abs);
    }

    public boolean safetyTest() {
        return abs;
    }

    public static void main(String[] args) {
        Car lada = new Lada(1.2, "MT", false, true);
        System.out.println(lada.safetyTest());
    }
}
