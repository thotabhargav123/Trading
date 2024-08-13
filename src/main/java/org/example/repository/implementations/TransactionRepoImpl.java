package org.example.repository.implementations;

import org.example.model.transaction.Transaction;
import org.example.repository.TransactionRepository;
import org.example.server.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepoImpl implements TransactionRepository {

    @Override
    public void createTransaction(Transaction transaction) {
        String sqlStatement = "Insert into Transaction (username, stockSymbol, quantity, date, transactionType) VALUES (?, ?, ?, ?, ?)";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlStatement)){

            ps.setString(1, transaction.getUsername());
            ps.setString(2, transaction.getStockSymbol());
            ps.setInt(3, transaction.getQuantity());
            ps.setTimestamp(4, Timestamp.valueOf(transaction.getDate()));
            ps.setString(5, transaction.getTransactionType());
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Transaction> getTransactionsByUsername(String username) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transactions WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Transaction transaction = new Transaction(
                        resultSet.getString("username"),
                        resultSet.getString("stockSymbol"),
                        resultSet.getInt("quantity"),
                        resultSet.getTimestamp("date").toLocalDateTime(),
                        resultSet.getString("transactionType")
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return transactions;
    }
}
