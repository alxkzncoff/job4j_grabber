package ru.job4j.ood.isp;

public class Engineer implements Employee {

    @Override
    public void takeTask(String task) {
        System.out.println(task + " taken.");
    }

    @Override
    public double getSalary(int hours) {
        int wagePerHour = 1000;
        return wagePerHour * hours * 21;
    }

    @Override
    public String writeCode() {
        throw new UnsupportedOperationException("Engineers don't write code!");
    }

    @Override
    public void createScheme() {
        System.out.println("Some beautiful scheme created.");
    }
}
