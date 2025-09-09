package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.event.ActionEvent;
import java.io.IOException;

public class ControllerHome {
    private SceneManager sceneManager;

    public void loginClick(ActionEvent event) throws IOException {
        sceneManager.showView("/fxml/login.fxml", "Login");
    }

    public void createAccountClick() {
        System.out.println("createAccountClick clicked");
    }

    public void forgotPasswordClick() {
        System.out.println("forgotPassword clicked");
    }

    public void initialize() {
        sceneManager = SceneManager.getScreenManager();
    }
}