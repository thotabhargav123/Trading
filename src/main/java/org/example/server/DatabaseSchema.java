package org.example.server;

import java.sql.*;

public class DatabaseSchema {

    private static final String URL = DatabaseConfig.JDBC_URL;
    private static final String USER = DatabaseConfig.JDBC_USER;
    private static final String PASSWORD = DatabaseConfig.JDBC_PASSWORD;
    private static final String DB_NAME = DatabaseConfig.DATABASE_NAME;

    public static void createDatabase() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Create database if not exists
            String createDatabase = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            statement.executeUpdate(createDatabase);
            System.out.println("Database created or already exists");

        } catch (SQLException e) {
            System.out.println("Error creating database: " + e.getMessage());
        }
    }

    public static void createSchema() {

        createDatabase();
        // Update URL to include the database name
        String dbUrl = URL + DB_NAME;
        try (Connection connection = DriverManager.getConnection(dbUrl, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Create Users table
            String createUsersTable = "CREATE TABLE IF NOT EXISTS Users (" +
                    "username VARCHAR(50) PRIMARY KEY, " +
                    "password VARCHAR(50), " +
                    "email VARCHAR(100), " +
                    "`role` VARCHAR(20) NOT NULL DEFAULT 'user'" + // Enclosed role in backticks
                    ")";
            statement.executeUpdate(createUsersTable);
            System.out.println("Users table created or already exists");

            // Create Stocks table
            String createStocksTable = "CREATE TABLE IF NOT EXISTS Stocks (" +
                    "symbol VARCHAR(10) PRIMARY KEY, " +
                    "price DOUBLE, " +
                    "quantity INT" +
                    ")";
            statement.executeUpdate(createStocksTable);
            System.out.println("Stocks table created or already exists");

            // Create Transactions table
            String createTransactionsTable = "CREATE TABLE IF NOT EXISTS Transactions (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(50), " +
                    "stockSymbol VARCHAR(10), " +
                    "quantity INT, " +
                    "date TIMESTAMP, " +
                    "transactionType VARCHAR(10), " +
                    "FOREIGN KEY (username) REFERENCES Users(username), " +
                    "FOREIGN KEY (stockSymbol) REFERENCES Stocks(symbol)" +
                    ")";
            statement.executeUpdate(createTransactionsTable);
            System.out.println("Transactions table created or already exists");

            // Create StockPriceHistory table
            String createStockPriceHistoryTable = "CREATE TABLE IF NOT EXISTS StockPriceHistory (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "stockSymbol VARCHAR(10), " +
                    "price DOUBLE, " +
                    "year INT, " +
                    "FOREIGN KEY (stockSymbol) REFERENCES Stocks(symbol)" +
                    ")";
            statement.executeUpdate(createStockPriceHistoryTable);
            System.out.println("StockPriceHistory table created or already exists");

            //Creating admin panel to change stock details.
            String checkAdminExists = "SELECT COUNT(*) AS count FROM users WHERE username = 'TrademasterAdmin'";
            ResultSet resultSet = statement.executeQuery(checkAdminExists);
            if (resultSet.next() && resultSet.getInt("count") == 0) {
                // If admin user does not exist, insert the admin user
                String insertAdminUser = "INSERT INTO users (username, password, email, role) VALUES (" +
                        "'TrademasterAdmin', " +
                        "'Trademaster', " +
                        "'AdminTrademaster@gmail.com', " +
                        "'admin'" +
                        ")";
                statement.execute(insertAdminUser);
                System.out.println("Admin user inserted successfully.");
            } else {
                System.out.println("Admin user already exists. Skipping insertion.");
            }

        } catch (SQLException e) {

            System.out.println("Error creating database schema: " + e.getMessage());
        }
    }
}
