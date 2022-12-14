package ru.levelp.at.lesson12.design.patterns.singleton;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.Getter;

public class DatabaseConnection {

    @Getter
    private final DataSource dataSource;

    public DatabaseConnection(String username, String password, String dbUrl) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(username);
        config.setPassword(password);
        dataSource = new HikariDataSource(config);
    }
}
