package org.example.repository.implementations;

import org.example.model.stock.Stock;
import org.example.repository.StockRepository;
import org.example.server.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockRepositoryImpl implements StockRepository {
    @Override
    public void createStock(Stock stock) {
        String sql = "INSERT INTO Stocks (symbol, price, quantity) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, stock.getSymbol());
            statement.setDouble(2, stock.getPrice());
            statement.setInt(3, stock.getQuantity());
            statement.executeUpdate();
        } catch (SQLException e) {

            System.out.println("Error creating stock: " + e.getMessage());
        }
    }

    @Override
    public Stock getStock(String symbol) {
        String sql = "SELECT * FROM Stocks WHERE symbol = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, symbol);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Stock stock = new Stock(
                        resultSet.getString("symbol"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                );
                return stock;
            }
        } catch (SQLException e) {
            System.out.println("Wrong Symbol Input");
        }
        return null;
    }

    @Override
    public List<Stock> getAllStocks() {
        List<Stock> stocks = new ArrayList<>();
        String sql = "SELECT * FROM Stocks";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Stock stock = new Stock(
                        resultSet.getString("symbol"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                );
                stocks.add(stock);
            }
        } catch (SQLException e) {
            System.out.println("Error in fetching");
        }
        return stocks;
    }

    @Override
    public void updateStock(Stock stock) {
        String sql = "UPDATE Stocks SET price = ?, quantity = ? WHERE symbol = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, stock.getPrice());
            statement.setInt(2, stock.getQuantity());
            statement.setString(3, stock.getSymbol());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating stock: " + e.getMessage());
        }
    }

    @Override
    public void deleteStock(String symbol) {
        String sql = "DELETE FROM Stocks WHERE symbol = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, symbol);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting stock: " + e.getMessage());
        }
    }


}
