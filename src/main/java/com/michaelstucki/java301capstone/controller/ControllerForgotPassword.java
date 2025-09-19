package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.dao.Dao;
import com.michaelstucki.java301capstone.dao.DaoSQLite;
import com.michaelstucki.java301capstone.dto.User;
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
    private Dao dao;

    public void recoverPasswordClick() {
        if (username.getText().isEmpty()) {
            userMessage.setText("username not entered!");
        } else {
            User user = dao.getUser(username.getText());
            if (user == null) {
                userMessage.setText("unrecognized username!");
            } else if (securityAnswer.getText().isEmpty()) {
                userMessage.setText("security answer not entered!");
            } else if (!securityAnswer.getText().equals(user.getSecurityAnswer())) {
                userMessage.setText("security answer is incorrect!");
            } else {
                userMessage.setTextFill(Color.GREEN);
                userMessage.setText("password: " + user.getPassword());
            }
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
        dao = DaoSQLite.getDao();

        username.focusedProperty().addListener((observable, oldValue, newValue) -> {
            userMessage.setText("");
        });

        securityAnswer.focusedProperty().addListener((observable, oldValue, newValue) -> {
            userMessage.setText("");
        });
    }
}