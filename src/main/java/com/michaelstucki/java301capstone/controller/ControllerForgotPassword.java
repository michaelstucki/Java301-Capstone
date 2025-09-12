package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ControllerForgotPassword {
    @FXML
    private TextField username;
    @FXML
    private Label loginMessage;
    @FXML
    private TextField securityAnswer;
    @FXML
    private Hyperlink home;
    @FXML
    private Hyperlink exit;
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
        loginMessage.setText("");
        loginMessage.setTextFill(Color.RED);
    }

    public void exitClick() {
        sceneManager.exit();
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