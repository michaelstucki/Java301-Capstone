package com.michaelstucki.java301capstone;

import javafx.scene.control.Hyperlink;

public class Controller {
    public Hyperlink login;
    public Hyperlink createAccount;
    public Hyperlink forgotPassword;

    public void loginClick() {
        System.out.println("login clicked");
    }

    public void createAccountClick() {
        System.out.println("create account clicked");
    }

    public void forgotPasswordloginClick() {
        System.out.println("forgot password clicked");
    }
}