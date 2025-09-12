package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.Objects;

public class ControllerLogin {
    public Hyperlink createAccount;
    public Hyperlink forgotPassword;
    public TextField username;
    public TextField password;
    public PasswordField passwordField;
    public ImageView eyeIcon;
    public Label loginMessage;
    private boolean passwordVisible = false;
    private SceneManager sceneManager;

    public void signInClick() {
        System.out.println("signInClick account clicked");

        String baduser = "xxx";
        String badpw = "???";

        if (!username.getText().equals(baduser)) {
            loginMessage.setText("unrecognized username!");
        } else if (!password.getText().equals(badpw)) {
            loginMessage.setText("invalid password!");
        } else {
            clearInputs();
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

    public void createAccountClick() {
        System.out.println("createAccount clicked");
        clearInputs();
        sceneManager.showView("/fxml/create_account.fxml");
    }

    public void forgotPasswordClick() {
        System.out.println("forgotPassword clicked");
        clearInputs();
        sceneManager.showView("/fxml/forgot_password.fxml");
    }

    private void clearInputs() {
        username.setText("");
        password.setText("");
    }

    public void exitClick() {
        sceneManager.exit();
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
    }

}