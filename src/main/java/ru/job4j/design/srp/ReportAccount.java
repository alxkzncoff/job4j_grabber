package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportAccount implements Report {

    private static final double EURO = 80.0;

    private Store store;

    public ReportAccount(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() / EURO).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
