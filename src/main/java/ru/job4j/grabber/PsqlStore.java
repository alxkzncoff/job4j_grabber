package ru.job4j.grabber;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Класс оперирует данными БД.
 * Сохраняет, выводит все данные, выводит данные по id.
 * @author Aleksandr Kuznetsov.
 * @version 1.0
 */
public class PsqlStore implements Store, AutoCloseable {

    private Connection cn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            cn = DriverManager.getConnection(
                    cfg.getProperty("jdbc.url"),
                    cfg.getProperty("jdbc.login"),
                    cfg.getProperty("jdbc.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Вспомогательный метод для обработки ResultSet.
     * @param rs ResultSet.
     * @return Item.
     * @throws SQLException
     */
    private Post resultSetHandle(ResultSet rs) throws SQLException {
        return new Post(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("link"),
                rs.getString("text"),
                rs.getTimestamp("created").toLocalDateTime());
    }

    /**
     * Метод сохраняет объявление в БД.
     * @param post объявление.
     */
    @Override
    public void save(Post post) {
        try (PreparedStatement ps = cn.prepareStatement(
                "insert into post(name, text, link, created) values(?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getDescription());
            ps.setString(3, post.getLink());
            ps.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            ps.execute();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    post.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает все объявления из БД.
     * @return List<Post> Список объявлений.
     */
    @Override
    public List<Post> getAll() {
        List<Post> result = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(
                "select * from post"
        )) {
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    result.add(resultSetHandle(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод ищет объявление по id.
     * @param id идентификационный номер объявление в БД.
     * @return Post объявление.
     */
    @Override
    public Post findById(int id) {
        Post result = null;
        try (PreparedStatement ps = cn.prepareStatement(
                "select * from post where id = ?"
        )) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSetHandle(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in = PsqlStore.class.getResourceAsStream("/grabber.properties")) {
            config.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PsqlStore store = new PsqlStore(config);
        Post post1 = new Post("test",
                "test link",
                "test description",
                LocalDateTime.now());
        Post post2 = new Post("test 1",
                "test link 1",
                "test description 1",
                LocalDateTime.now());
        store.save(post1);
        store.save(post2);
        store.getAll().forEach(System.out::println);
        System.out.println(store.findById(post1.getId()));
    }
}
