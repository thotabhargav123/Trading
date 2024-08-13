package org.example.model.user;

public class User {
    private String username;
    private String password;
    private String email;
    private String role;
    public User(String username, String password, String email,String role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // getters
    public String getUsername(){
        return this.username;
    }

    public  String getPassword(){
        return this.password;
    }

    public String getEmail(){
        return  this.email;
    }

    //setters

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return ("The user is added having details username " + this.username + " password " + this.password + " email " + this.email );
    }
}
