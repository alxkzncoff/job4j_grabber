package ru.job4j.ood.ocp;

/**
 * Пример нарушения принципа OCP.
 */

public class VehicleFactory {

    private static class CarBuilder {
        public void addWheels() {
        }

        public void build() {
        }
    }

    public static void main(String[] args) {
        CarBuilder builder = new CarBuilder();
        builder.addWheels();
        builder.build();
    }
}
