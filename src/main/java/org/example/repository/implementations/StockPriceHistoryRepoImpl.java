package org.example.repository.implementations;

import org.example.model.stockPriceHistory.StockPriceHistory;
import org.example.repository.StockPriceHistoryRepository;
import org.example.server.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockPriceHistoryRepoImpl implements StockPriceHistoryRepository {
    @Override
    public void addStockPriceHistory(StockPriceHistory stockPriceHistory) {
        String sql = "INSERT INTO StockPriceHistory (stockSymbol, price, year) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, stockPriceHistory.getStockSymbol());
            statement.setDouble(2, stockPriceHistory.getPrice());
            statement.setInt(3, stockPriceHistory.getYear());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding stock price history: " + e.getMessage());
        }
    }

    @Override
    public List<StockPriceHistory> getStockPriceHistory(String stockSymbol, int fromYear, int toYear) {
        List<StockPriceHistory> historyList = new ArrayList<>();
        String sql = "SELECT * FROM StockPriceHistory WHERE stockSymbol = ? AND year BETWEEN ? AND ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, stockSymbol);
            statement.setInt(2, fromYear);
            statement.setInt(3, toYear);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                StockPriceHistory history = new StockPriceHistory(
                        resultSet.getString("stockSymbol"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("year")
                );
                historyList.add(history);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return historyList;
    }
}
