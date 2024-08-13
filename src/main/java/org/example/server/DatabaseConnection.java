package org.example.server;

import org.example.model.user.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = DatabaseConfig.JDBC_URL; // The base URL
    private static final String USER = DatabaseConfig.JDBC_USER;
    private static final String PASSWORD = DatabaseConfig.JDBC_PASSWORD;

    public static Connection getConnection() throws SQLException {
        // Use the full URL including the database name
        String dbUrl = URL + DatabaseConfig.DATABASE_NAME;
        System.out.println("Connection Successful");
        return DriverManager.getConnection(dbUrl, USER, PASSWORD);
    }
}
