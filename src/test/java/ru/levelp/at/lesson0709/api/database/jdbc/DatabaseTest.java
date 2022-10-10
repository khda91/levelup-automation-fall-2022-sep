package ru.levelp.at.lesson0709.api.database.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DatabaseTest {

    private PostgresClient client;

    @BeforeEach
    void setUp() {
        client = PostgresClient.getInstance();
    }

    @Test
    void selectRecordsTest() {
        try (Statement statement = client.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from person");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " || "
                    + resultSet.getString(2) + " || " + resultSet.getString(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        PostgresClient.getInstance().close();
        client = null;
    }
}

// Cоздание и закрытие statement
//        Statement statement = null;
//        try {
//            statement = client.getConnection().createStatement();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
