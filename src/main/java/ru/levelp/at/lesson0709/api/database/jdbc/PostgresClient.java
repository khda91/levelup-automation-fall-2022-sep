package ru.levelp.at.lesson0709.api.database.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class PostgresClient {

    private static PostgresClient instance;

    private Connection connection;
    private final Properties dbProperties;

    private PostgresClient() {
        dbProperties = new Properties();
        try {
            dbProperties.load(this.getClass().getResourceAsStream("/ru/levelp/at/lesson0709/api/"
                + "database/database.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(dbProperties.getProperty("url"),
                dbProperties.getProperty("username"), dbProperties.getProperty("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static PostgresClient getInstance() {
        if (instance == null) {
            instance = new PostgresClient();
            instance.createConnection();
        }
        return instance;
    }
}
