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
            result.setDescription(tdComments.get(1).text());
            result.setCreated(dateTimeParser.parser(
                    tdDates.get(0).text().split(", ")[0]
                            + ", "
                            + tdDates.get(0).text().split(", ")[1].split(" ")[0]));
            result.setLink(link);
            result.setTitle(tdTitle.get(0).text());
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
            Document doc = Jsoup.connect(link).get();
            Elements tdLinks = doc.select(".postslisttopic");
            for (int i = 3; i < tdLinks.size(); i++) {
                Post post = detail(tdLinks.get(i).child(0).attr("href"));
                result.add(post);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        SqlRuParser parser = new SqlRuParser(new SqlRuDateTimeParser());
        System.out.println(parser.list("https://www.sql.ru/forum/job-offers"));
    }
}
