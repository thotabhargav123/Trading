package org.example.serviceLayer;

import org.example.model.user.User;

public interface UserService {
    void createUser(User user);
    User getUser(String username);
    void updateUser(User user);
    void deleteUser(String username);
    User login(String username, String password);
    boolean isAdmin(User user);
}