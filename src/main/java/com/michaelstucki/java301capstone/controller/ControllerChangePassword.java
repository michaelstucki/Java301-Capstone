package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.Objects;

public class ControllerChangePassword {
    @FXML
    private TextField username;
    @FXML
    private TextField oldPassword;
    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private ImageView oldEyeIcon;
    @FXML
    private TextField password;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordRetype;
    @FXML
    private ImageView eyeIcon;
    @FXML
    private Label userMessage;
    private boolean passwordVisible = false;
    private boolean oldPasswordVisible = false;
    private SceneManager sceneManager;

    public void changePasswordClick() {
        System.out.println("changePasswordClick clicked");

        String user = "x";
        String demoOldPw = "?";

        if (username.getText().isEmpty()) {
            userMessage.setText("username not entered!");
        } else if (!username.getText().equals(user)) {
            userMessage.setText("unrecognized username!");
        } else if (oldPassword.getText().isEmpty()) {
            userMessage.setText("old password not entered!");
        } else if (!oldPassword.getText().equals(demoOldPw)) {
            userMessage.setText("invalid old password!");
        } else if (password.getText().isEmpty()) {
            userMessage.setText("new password not entered!");
        } else if (!passwordRetype.getText().equals(password.getText())) {
            userMessage.setText("new passwords do not match!");
        } else {
            userMessage.setTextFill(Color.GREEN);
            userMessage.setText("password changed!");
        }
    }

    public void OldTogglePasswordVisibility() {
        if (oldPasswordVisible) {
            oldPasswordField.setText(oldPassword.getText()); // Sync text
            oldPasswordField.setVisible(true);
            oldPassword.setVisible(false);
            eyeIcon.setImage(new Image(Objects.requireNonNull(getClass().
                    getResourceAsStream("/images/eye_closed.jpg"))));
        } else {
            oldPassword.setText(oldPasswordField.getText()); // Sync text
            oldPasswordField.setVisible(false);
            oldPassword.setVisible(true);
            oldEyeIcon.setImage(new Image(Objects.requireNonNull(getClass().
                    getResourceAsStream("/images/eye_open.jpg"))));
        }
        oldPasswordVisible = !oldPasswordVisible;
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

    public void homeClick() {
        clearInputs();
        sceneManager.showView("/fxml/home.fxml");
    }

    private void clearInputs() {
        username.setText("");
        password.setText("");
        passwordRetype.setText("");
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

        oldPassword.textProperty().bindBidirectional(oldPasswordField.textProperty());

        oldPassword.focusedProperty().addListener((observable, oldValue, newValue) -> {
            userMessage.setText("");
        });

        oldPasswordField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            userMessage.setText("");
        });

        password.textProperty().bindBidirectional(passwordField.textProperty());

        password.focusedProperty().addListener((observable, oldValue, newValue) -> {
            userMessage.setText("");
        });

        passwordField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            userMessage.setText("");
        });

        passwordRetype.focusedProperty().addListener((observable, oldValue, newValue) -> {
            userMessage.setText("");
        });
    }
}