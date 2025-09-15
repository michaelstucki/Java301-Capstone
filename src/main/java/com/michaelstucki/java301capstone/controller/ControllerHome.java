package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ControllerHome {
    @FXML
    public Hyperlink exit;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView eyeIcon;
    @FXML
    private Label userMessage;
    private boolean passwordVisible = false;
    private SceneManager sceneManager;

    public void init() {}

    public void signInClick() {
        sceneManager.showView("/fxml/decks.fxml");
//
//        String user = "ccc";
//        String pw = "111";
//
//        if (!username.getText().equals(user)) {
//            userMessage.setText("unrecognized username!");
//        } else if (!password.getText().equals(pw)) {
//            userMessage.setText("invalid password!");
//        } else {
//            clearInputs();
//            sceneManager.showView("/fxml/decks.fxml");
//        }
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
            userMessage.setText("");
        });

        password.focusedProperty().addListener((observable, oldValue, newValue) -> {
            userMessage.setText("");
        });

        passwordField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            userMessage.setText("");
        });
    }
}