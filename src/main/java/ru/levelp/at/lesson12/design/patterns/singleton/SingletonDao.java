package ru.levelp.at.lesson12.design.patterns.singleton;

import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;

public final class SingletonDao {

    private static SingletonDao instance;

    @Getter
    private final JdbcTemplate jdbcTemplate;

    private SingletonDao() {
        DatabaseConnection connection = new DatabaseConnection("", "", "");
        this.jdbcTemplate = new JdbcTemplate(connection.getDataSource());
    }

    public static synchronized SingletonDao getInstance() {
        if (instance == null) {
            instance = new SingletonDao();
        }
        return instance;
    }
}
