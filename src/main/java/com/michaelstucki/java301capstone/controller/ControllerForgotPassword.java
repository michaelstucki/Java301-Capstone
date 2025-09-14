package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ControllerForgotPassword {
    @FXML
    private TextField username;
    @FXML
    private Label userMessage;
    @FXML
    private TextField securityAnswer;
    private SceneManager sceneManager;

    public void recoverPasswordClick() {
        System.out.println("createAccountClick clicked");

        String user = "xxx";
        String answer = "blue";
        String pw = "5xGollum";

        if (username.getText().isEmpty()) {
            userMessage.setText("username not entered!");
        } else if (!username.getText().equals(user)) {
            userMessage.setText("username does not exist!");
        } else if (securityAnswer.getText().isEmpty()) {
            userMessage.setText("security answer not entered!");
        } else if (!securityAnswer.getText().equals(answer)) {
            userMessage.setText("security answer is incorrect!");
        } else {
            userMessage.setTextFill(Color.GREEN);
            userMessage.setText("password: " + pw);
            clearInputs();
        }
    }

    public void loginClick() {
        clearInputs();
        sceneManager.showView("/fxml/home.fxml");
    }

    private void clearInputs() {
        username.setText("");
        securityAnswer.setText("");
        userMessage.setText("");
        userMessage.setTextFill(Color.RED);
    }

    public void exitClick() {
        sceneManager.exit();
    }

    public void initialize() {
        sceneManager = SceneManager.getScreenManager();
        username.focusedProperty().addListener((observable, oldValue, newValue) -> {
            userMessage.setText("");
        });

        securityAnswer.focusedProperty().addListener((observable, oldValue, newValue) -> {
            userMessage.setText("");
        });
    }
}