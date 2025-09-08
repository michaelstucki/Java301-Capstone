package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.SceneManager;
import javafx.event.ActionEvent;
import java.io.IOException;

public class ControllerHome {
    private SceneManager sceneManager;

    public void loginClick(ActionEvent event) throws IOException {
        System.out.println("loginClick clicked");
        sceneManager.showView("/fxml/login.fxml", "Login");
    }

    public void createAccountClick() {
        System.out.println("createAccount clicked");
    }

    public void forgotPasswordClick() {
        System.out.println("forgotPassword clicked");
    }

    public void initialize() {
        System.out.println("initialize");
        sceneManager = SceneManager.getScreenManager();
    }
}