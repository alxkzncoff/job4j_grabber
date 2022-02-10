package ru.job4j.design.srp;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Calendar;

import static org.junit.Assert.*;

public class ReportXMLTest {

    @Test
    public void whenXMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        LocalDateTime date = LocalDateTime.ofInstant(now.toInstant(), now.getTimeZone().toZoneId());
        Employee worker = new Employee("Alex", now, now, 150);
        store.add(worker);
        Report engine = new ReportXML(store);
        String actual = engine.generate(em -> true);
        StringBuilder expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employee name=\"").append(worker.getName()).append("\"")
                .append(" hired=\"").append(date).append("+03:00\"")
                .append(" fired=\"").append(date).append("+03:00\"")
                .append(" salary=\"").append(worker.getSalary()).append("\"")
                .append("/>\n");
        assertEquals(expected.toString(), actual);
    }
}