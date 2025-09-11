package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;
import javafx.scene.paint.Color;

public class ControllerCreateAccount {
    public TextField username;
    public TextField password;
    public PasswordField passwordField;
    public ImageView eyeIcon;
    public Label loginMessage;
    public PasswordField passwordRetype;
    public TextField securityAnswer;
    public Hyperlink login;
    private boolean passwordVisible = false;
    private SceneManager sceneManager;

    public void createAccountClick() {
        System.out.println("createAccountClick clicked");

        String user = "xxx";

        if (username.getText().isEmpty()) {
            loginMessage.setText("username not entered!");
        } else if (username.getText().equals(user)) {
            loginMessage.setText("username is taken!");
        } else if (password.getText().isEmpty()) {
            loginMessage.setText("password not entered!");
        } else if (!passwordRetype.getText().equals(password.getText())) {
            loginMessage.setText("passwords do not match!");
        } else if (securityAnswer.getText().isEmpty()) {
            loginMessage.setText("security question not answered!");
        } else {
            loginMessage.setTextFill(Color.BLUE);
            loginMessage.setText("account created!");
        }
    }

    public void togglePasswordVisibility() {
        if (passwordVisible) {
            passwordField.setText(password.getText()); // Sync text
            passwordField.setVisible(true);
            password.setVisible(false);
            eyeIcon.setImage(new Image(Objects.requireNonNull(getClass().
                    getResourceAsStream("/images/eye_closed.jpg"))));
        } else {
            password.setText(passwordField.getText()); // Sync text
            passwordField.setVisible(false);
            password.setVisible(true);
            eyeIcon.setImage(new Image(Objects.requireNonNull(getClass().
                    getResourceAsStream("/images/eye_open.jpg"))));
        }
        passwordVisible = !passwordVisible;
    }

    public void loginClick() {
        username.setText("");
        password.setText("");
        passwordRetype.setText("");
        securityAnswer.setText("");
        loginMessage.setText("");
        loginMessage.setTextFill(Color.RED);
        sceneManager.showView("/fxml/login.fxml");
    }

    public void initialize() {
        sceneManager = SceneManager.getScreenManager();
        password.textProperty().bindBidirectional(passwordField.textProperty());
        username.focusedProperty().addListener((observable, oldValue, newValue) -> {
            loginMessage.setText("");
        });

        password.focusedProperty().addListener((observable, oldValue, newValue) -> {
            loginMessage.setText("");
        });

        passwordField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            loginMessage.setText("");
        });

        passwordRetype.focusedProperty().addListener((observable, oldValue, newValue) -> {
            loginMessage.setText("");
        });

        securityAnswer.focusedProperty().addListener((observable, oldValue, newValue) -> {
            loginMessage.setText("");
        });
    }
}