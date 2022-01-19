package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Parse;
import ru.job4j.grabber.Post;
import ru.job4j.utils.DateTimeParser;
import ru.job4j.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс собирает Java вакансии с сайта sql.ru.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */
public class SqlRuParser implements Parse {

    private final DateTimeParser dateTimeParser;

    public SqlRuParser(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    /**
     * Метод загружает детали поста.
     * @param link ссылка на пост.
     * @return Post
     */
    @Override
    public Post detail(String link) {
        Post result = new Post();
        try {
            Document doc = Jsoup.connect(link).get();
            Elements tdTitle = doc.select(".messageHeader");
            Elements tdComments = doc.select(".msgBody");
            Elements tdDates = doc.select(".msgFooter");
            String date = tdDates.text()
                    .substring(0, tdDates.text().indexOf("["))
                    .trim();
            result.setDescription(tdComments.get(1).text());
            result.setCreated(dateTimeParser.parser(date));
            result.setLink(link);
            String title = tdTitle.get(0).text()
                    .substring(0, tdTitle.text().indexOf("["))
                    .trim();
            result.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод загружает список всех постов.
     * @param link ссылка на раздел с вакансиями.
     * @return List список постов.
     */
    @Override
    public List<Post> list(String link) {
        List<Post> result = new ArrayList<>();
        try {
            for (int i = 1; i <= 5; i++) {
                Document doc = Jsoup.connect(link + "/" + i).get();
                Elements tdLinks = doc.select(".postslisttopic");
                tdLinks.stream()
                        .filter(l -> l.text().toLowerCase().contains("java")
                                && !l.text().toLowerCase().contains("javascript"))
                        .map(tdLink -> detail(tdLink.child(0).attr("href")))
                        .forEach(result::add);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        SqlRuParser parser = new SqlRuParser(new SqlRuDateTimeParser());
        parser.list("https://www.sql.ru/forum/job-offers").forEach(System.out::println);
    }
}
