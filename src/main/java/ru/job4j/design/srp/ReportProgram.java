package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportProgram implements Report {

    private Store store;

    public ReportProgram(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html><body><table><tr>"
                + "<th>Name</th>"
                + "<th>Hired</th>"
                + "<th>Fired</th>"
                + "<th>Salary<th>"
                + "</tr>");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append("<tr>")
                    .append("<td>").append(employee.getName()).append("</td>")
                    .append(System.lineSeparator())
                    .append("<td>").append(employee.getHired()).append("</td>")
                    .append(System.lineSeparator())
                    .append("<td>").append(employee.getFired()).append("</td>")
                    .append(System.lineSeparator())
                    .append("<td>").append(employee.getSalary()).append("</td>")
                    .append("</tr>");
        }
        text.append("</table></body></html>");
        return text.toString();
    }
}
