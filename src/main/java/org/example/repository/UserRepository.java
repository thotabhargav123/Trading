package org.example.repository;

import org.example.model.user.User;

import java.util.Optional;

public interface UserRepository {
    void createUser(User user);
    User getUser(String username);
    void updateUser(User user);
    void deleteUser(String username);

}
