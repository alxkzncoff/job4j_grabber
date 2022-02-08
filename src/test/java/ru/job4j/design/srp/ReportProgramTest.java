package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportProgramTest {

    @Test
    public void whenProgramGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Alex", now, now, 150);
        store.add(worker);
        Report engine = new ReportProgram(store);
        StringBuilder expect = new StringBuilder()
                .append("<html><body><table><tr>"
                        + "<th>Name</th>"
                        + "<th>Hired</th>"
                        + "<th>Fired</th>"
                        + "<th>Salary<th>"
                        + "</tr>")
                        .append(System.lineSeparator())
                .append("<tr>")
                .append("<td>").append(worker.getName()).append("</td>")
                .append(System.lineSeparator())
                .append("<td>").append(worker.getHired()).append("</td>")
                .append(System.lineSeparator())
                .append("<td>").append(worker.getFired()).append("</td>")
                .append(System.lineSeparator())
                .append("<td>").append(worker.getSalary()).append("</td>")
                .append("</tr>")
                .append("</table></body></html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

}