package org.example.serviceLayer.Impl;

import org.example.model.user.User;
import org.example.repository.UserRepository;
import org.example.serviceLayer.UserService;

import java.util.Objects;

public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public void createUser(User user) {
        userRepository.createUser(user);
    }

    @Override
    public User getUser(String username) {
        if(Objects.equals(username, "")){
            System.out.println("Username Cant be empty");
        }
        User user = userRepository.getUser(username);
        if(user == null){
            System.out.println("Wrong or invalid username");
            return null;
        }
        return  user;
    }

    @Override
    public void updateUser(User user) {
        if(user.getPassword() == null || user.getEmail() == null){
            System.out.println("Enter valid user details");
        }
        else {
            userRepository.updateUser(user);
        }
    }

    @Override
    public void deleteUser(String username) {
        if(username == null){
            System.out.println("Enter valid username to delete");
        }
        userRepository.deleteUser(username);
    }

    @Override
    public User login(String username, String password) {
        System.out.println("In service Layer login fn");
        User user = userRepository.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;  // Login successful
        } else {
            throw new IllegalArgumentException("Invalid username or password");
        }
    }

    @Override
    public boolean isAdmin(User user) {
        System.out.println("The user role is " + user.getRole());
        return "admin".equalsIgnoreCase(user.getRole());
    }
}
