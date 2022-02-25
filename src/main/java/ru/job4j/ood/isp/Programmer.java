package ru.job4j.ood.isp;

public class Programmer implements Employee {

    @Override
    public void takeTask(String task) {
        System.out.println(task + " taken.");
    }

    @Override
    public double getSalary(int hours) {
        int wagePerHour = 1500;
        return wagePerHour * hours * 21;
    }

    @Override
    public String writeCode() {
        return "Some amazing code!";
    }

    @Override
    public void createScheme() {
        throw new UnsupportedOperationException("Programmers don't create schemes!");
    }
}
