package com.michaelstucki.java301capstone.dto;

public class User {
    String username;
    String password;
    String securityAnswer;

    public User(String username, String password, String securityAnswer) {
        this.username = username;
        this.password = password;
        this.securityAnswer = securityAnswer;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getSecurityAnswer() { return securityAnswer; }
}
