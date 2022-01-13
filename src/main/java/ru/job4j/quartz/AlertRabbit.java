package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    private static int interval;

    private static Connection connection;

    public static void setConnection(Connection connection) {
        AlertRabbit.connection = connection;
    }

    private static void init() {
        Properties config = new Properties();
        try (InputStream in = AlertRabbit.class.getResourceAsStream("/rabbit.properties")) {
            config.load(in);
            interval = Integer.parseInt(config.getProperty("rabbit.interval"));
            Class.forName(config.getProperty("jdbc.driver"));
            Connection cn = DriverManager.getConnection(
                    config.getProperty("jdbc.url"),
                    config.getProperty("jdbc.login"),
                    config.getProperty("jdbc.password")
            );
            setConnection(cn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        init();
        try {
            List<Long> store = new ArrayList<>();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("store", store);
            data.put("connection", connection);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(interval)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
            System.out.println(store);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Rabbit implements Job {

        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            LocalDateTime created = LocalDateTime.now();
            Connection cn = (Connection) context.getJobDetail().getJobDataMap().get("connection");
            System.out.println("Rabbit runs here ...");
            List<Long> store = (List<Long>) context.getJobDetail().getJobDataMap().get("store");
            store.add(System.currentTimeMillis());
            try (PreparedStatement ps = cn.prepareStatement(
                    "insert into rabbit(created) values (?)"
            )) {
                ps.setTimestamp(1, Timestamp.valueOf(created));
                ps.execute();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
