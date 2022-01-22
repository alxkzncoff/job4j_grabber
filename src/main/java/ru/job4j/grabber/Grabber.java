package ru.job4j.grabber;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ru.job4j.html.SqlRuParser;
import ru.job4j.utils.SqlRuDateTimeParser;

import java.io.*;
import java.util.Properties;
import java.util.List;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Программа считывает Java вакансии с sql.ru и записывает их в БД.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */
public class Grabber implements Grab {

    private final Properties cfg = new Properties();

    /**
     * Метод инициализирует работу с БД.
     * @return Store
     */
    public Store store() {
        return new PsqlStore(cfg);
    }

    public Scheduler scheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        return scheduler;
    }

    /**
     * Метод загружает файл конфигураций.
     * @throws IOException
     */
    public void cfg() {
        try (InputStream in = Grabber.class.getResourceAsStream("/app.properties")) {
            cfg.load(in);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void init(Parse parse, Store store, Scheduler scheduler) throws SchedulerException, InterruptedException {
        JobDataMap data = new JobDataMap();
        data.put("store", store);
        data.put("parse", parse);
        JobDetail job = newJob(GrabJob.class)
                .usingJobData(data)
                .build();
        SimpleScheduleBuilder times = simpleSchedule()
                .withIntervalInSeconds(Integer.parseInt(cfg.getProperty("time")))
                .repeatForever();
        Trigger trigger = newTrigger()
                .startNow()
                .withSchedule(times)
                .build();
        scheduler.scheduleJob(job, trigger);
        Thread.sleep(10000);
        scheduler.shutdown();
    }

    public static class GrabJob implements Job {

        @Override
        public void execute(JobExecutionContext context) {
            JobDataMap map = context.getJobDetail().getJobDataMap();
            Store store = (Store) map.get("store");
            Parse parse = (Parse) map.get("parse");
            List<Post> posts = parse.list("https://www.sql.ru/forum/job-offers");
            posts.forEach(store::save);
        }
    }

    public static void main(String[] args) throws Exception {
        Grabber grab = new Grabber();
        grab.cfg();
        Scheduler scheduler = grab.scheduler();
        Store store = grab.store();
        grab.init(new SqlRuParser(new SqlRuDateTimeParser()), store, scheduler);
    }
}
