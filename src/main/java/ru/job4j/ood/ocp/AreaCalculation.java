package ru.job4j.ood.ocp;

/**
 * Пример нарушения принципа OCP.
 */

public class AreaCalculation {

    public static class Circle {
    }

    public static class AreaCalculator {

        public static int calculate(Circle circle) {
            return 0;
        }
    }

    public static void main(String[] args) {
        Circle circle = new Circle();
        System.out.println(AreaCalculator.calculate(circle));
    }
}
