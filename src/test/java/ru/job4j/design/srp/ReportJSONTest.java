package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.junit.Assert.*;

public class ReportJSONTest {

    @Test
    public void whenJSONGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Gson gson = new GsonBuilder().create();
        Employee worker = new Employee("Alex", now, now, 100);
        store.add(worker);
        Report engine = new ReportJSON(store);
        String actual = engine.generate(em -> true);
        StringBuilder expected = new StringBuilder()
                .append("[{")
                .append("\"name\":").append("\"").append(worker.getName()).append("\",")
                .append("\"hired\":").append(gson.toJson(worker.getHired())).append(",")
                .append("\"fired\":").append(gson.toJson(worker.getFired())).append(",")
                .append("\"salary\":").append(worker.getSalary())
                .append("}]");
        assertEquals(expected.toString(), actual);
    }

}