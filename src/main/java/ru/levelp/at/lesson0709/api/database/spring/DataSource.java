package ru.levelp.at.lesson0709.api.database.spring;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.util.Properties;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static javax.sql.DataSource ds;

    static {
        var dbProperties = new Properties();
        try {
            dbProperties.load(DataSource.class.getResourceAsStream("/ru/levelp/at/lesson0709/api/"
                + "database/database.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        config.setJdbcUrl(dbProperties.getProperty("url"));
        config.setUsername(dbProperties.getProperty("username"));
        config.setPassword(dbProperties.getProperty("password"));
        ds = new HikariDataSource(config);
    }

    public static javax.sql.DataSource getDataSource() {
        return ds;
    }
}
