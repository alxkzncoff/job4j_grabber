package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParser {
    public static void main(String[] args) throws Exception {
        int pages = 5;
        for (int i = 0; i <= pages; i++) {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + pages).get();
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
