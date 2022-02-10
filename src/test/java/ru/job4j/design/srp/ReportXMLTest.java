package ru.job4j.design.srp;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.Assert.*;

public class ReportXMLTest {

    @Test
    public void whenXMLGenerated() {
        MemStore store = new MemStore();
        Calendar date = new GregorianCalendar(2022, Calendar.JANUARY, 1);
        date.setTimeZone(TimeZone.getTimeZone(ZoneOffset.of("+3")));
        Employee worker = new Employee("Alex", date, date, 150);
        store.add(worker);
        Report engine = new ReportXML(store);
        String actual = engine.generate(em -> true);
        StringBuilder expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employee name=\"").append(worker.getName()).append("\"")
                .append(" hired=\"2022-01-01T00:00:00+03:00\"")
                .append(" fired=\"2022-01-01T00:00:00+03:00\"")
                .append(" salary=\"").append(worker.getSalary()).append("\"")
                .append("/>\n");
        assertEquals(expected.toString(), actual);
    }
}