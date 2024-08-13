package org.example.repository.implementations;

import org.example.model.user.User;
import org.example.repository.UserRepository;
import org.example.server.DatabaseConnection;

import java.sql.*;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {

            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    @Override
    public User getUser(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("role")
                );
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Some Error in username");
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE Users SET password = ?, email = ? WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getPassword());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(String username) {
        String sql = "DELETE FROM Users WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }
}
