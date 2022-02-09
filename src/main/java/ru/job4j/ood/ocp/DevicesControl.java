package ru.job4j.ood.ocp;

/**
 * Пример нарушения принципа OCP.
 */

public class DevicesControl {

    private static class TV {
        public void switchChannel() {
        }

        public void setupColor() {
        }

    }

    public static void main(String[] args) {
        TV tv = new TV();
        tv.setupColor();
        tv.switchChannel();
    }
}
