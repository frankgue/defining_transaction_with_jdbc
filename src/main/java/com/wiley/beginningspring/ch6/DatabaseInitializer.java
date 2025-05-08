package com.wiley.beginningspring.ch6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void init() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
             Statement stmt = conn.createStatement()) {

            String ddl = "CREATE TABLE IF NOT EXISTS ACCOUNT (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "owner_name VARCHAR(100)," +
                    "balance DOUBLE," +
                    "access_time TIMESTAMP," +
                    "locked BOOLEAN);";
            stmt.executeUpdate(ddl);

            stmt.executeUpdate("INSERT INTO ACCOUNT(id, owner_name, balance, access_time, locked) VALUES (100, 'owner-1', 10, CURRENT_TIMESTAMP, false)");
            stmt.executeUpdate("INSERT INTO ACCOUNT(id, owner_name, balance, access_time, locked) VALUES (101, 'owner-2', 0, CURRENT_TIMESTAMP, false)");

            System.out.println("Base de données initialisée.");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur d'initialisation de la base de données", e);
        }
    }
}
