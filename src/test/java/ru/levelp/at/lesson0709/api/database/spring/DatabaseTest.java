package ru.levelp.at.lesson0709.api.database.spring;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseTest {

    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate = new JdbcTemplate(DataSource.getDataSource());
    }

    @Test
    void selectRecordsTest() {
        //        try (Statement statement = client.getConnection().createStatement()) {
        //            ResultSet resultSet = statement.executeQuery("select * from person");
        //            while (resultSet.next()) {
        //                System.out.println(resultSet.getString("id") + " || "
        //                    + resultSet.getString(2) + " || " + resultSet.getString(5));
        //            }
        //        } catch (SQLException e) {
        //            throw new RuntimeException(e);
        //        }

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM person");

        maps.forEach(System.out::println);
    }
}
