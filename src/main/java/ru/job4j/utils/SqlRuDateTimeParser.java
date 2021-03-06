package ru.job4j.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final Map<String, String> MONTHS = Map.ofEntries(
            Map.entry("янв", "01"),
            Map.entry("фев", "02"),
            Map.entry("мар", "03"),
            Map.entry("апр", "04"),
            Map.entry("май", "05"),
            Map.entry("июн", "06"),
            Map.entry("июл", "07"),
            Map.entry("авг", "08"),
            Map.entry("сен", "09"),
            Map.entry("окт", "10"),
            Map.entry("ноя", "11"),
            Map.entry("дек", "12"));

    private static final String TODAY = "сегодня";
    private static final String YESTERDAY = "вчера";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d-MM-yy HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d-MM-yy");


    /**
     * Метод преобразует строку в дату.
     *
     * @param parse строка, которую необходимо преобразовать.
     * @return Дата.
     */
    @Override
    public LocalDateTime parser(String parse) {
        String dateTime;
        String date = parse.split(",")[0];
        String time = parse.split(",")[1];
        if (TODAY.equals(date)) {
            dateTime = LocalDate.now().format(DATE_FORMATTER)
                    + time;
        } else if (YESTERDAY.equals(date)) {
            dateTime = LocalDate.now().minusDays(1).format(DATE_FORMATTER)
                    + time;
        } else {
            dateTime = date.split(" ")[0]
                    + "-" + MONTHS.get(date.split(" ")[1])
                    + "-" + date.split(" ")[2]
                    + time;
        }
        return LocalDateTime.parse(dateTime, FORMATTER);
    }
}
