package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ControllerForgotPassword {
    public TextField username;
    public Label loginMessage;
    public TextField securityAnswer;
    private SceneManager sceneManager;

    public void recoverPasswordClick() {
        System.out.println("createAccountClick clicked");

        String user = "xxx";
        String answer = "blue";
        String pw = "5xGollum";

        if (username.getText().isEmpty()) {
            loginMessage.setText("username not entered!");
        } else if (!username.getText().equals(user)) {
            loginMessage.setText("username does not exist!");
        } else if (securityAnswer.getText().isEmpty()) {
            loginMessage.setText("security answer not entered!");
        } else if (!securityAnswer.getText().equals(answer)) {
            loginMessage.setText("security answer is incorrect!");
        } else {
            loginMessage.setTextFill(Color.BLUE);
            loginMessage.setText("password: " + pw);
        }
    }

    public void loginClick() {
        username.setText("");
        securityAnswer.setText("");
        loginMessage.setText("");
        loginMessage.setTextFill(Color.RED);
        sceneManager.showView("/fxml/login.fxml");
    }

    public void initialize() {
        sceneManager = SceneManager.getScreenManager();
        username.focusedProperty().addListener((observable, oldValue, newValue) -> {
            loginMessage.setText("");
        });

        securityAnswer.focusedProperty().addListener((observable, oldValue, newValue) -> {
            loginMessage.setText("");
        });
    }
}