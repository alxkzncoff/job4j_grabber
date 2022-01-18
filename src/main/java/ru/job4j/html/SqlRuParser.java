package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Post;
import ru.job4j.utils.SqlRuDateTimeParser;

import java.io.IOException;

public class SqlRuParser {

    /**
     * Метод загружает детали поста.
     * @param link ссылка на пост.
     * @return Post
     */
    public static Post detail(String link) {
        Post result = new Post();
        try {
            Document doc = Jsoup.connect(link).get();
            Elements tdComments = doc.select(".msgBody");
            Elements tdDates = doc.select(".msgFooter");
            SqlRuDateTimeParser dateTimeParser = new SqlRuDateTimeParser();
            result.setDescription(tdComments.get(1).text());
            result.setCreated(dateTimeParser.parser(
                    tdDates.get(1).text().split(", ")[0]
                            + ", "
                            + tdDates.get(1).text().split(", ")[1].split(" ")[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i <= 5; i++) {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + i).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td: row) {
                Element parent = td.parent();
                Element href = td.child(0);
                System.out.println(href.attr("href"));
                System.out.println(href.text());
                System.out.println(parent.children().get(5).text());
            }
        }
    }
}
