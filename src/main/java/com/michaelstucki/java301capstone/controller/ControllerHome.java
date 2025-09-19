package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.dao.Dao;
import com.michaelstucki.java301capstone.dao.DaoSQLite;
import com.michaelstucki.java301capstone.dto.User;
import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.event.ActionEvent;
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
    private Dao dao;

    public void signInClick() {
        if (username.getText().isEmpty()) {
            userMessage.setText("username not entered!");
        } else {
            User user = dao.getUser(username.getText());
            if (user == null) {
                userMessage.setText("unrecognized username!");
            } else if (!password.getText().equals(user.getPassword())) {
                userMessage.setText("invalid password!");
            } else {
                System.out.println("successful login!");
                sceneManager.showView("/fxml/decks.fxml");
            }
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
        clearInputs();
        sceneManager.showView("/fxml/create_account.fxml");
    }

    public void changePasswordClick() {
        clearInputs();
        sceneManager.showView("/fxml/change_password.fxml");
    }

    public void forgotPasswordClick() {
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
        dao = DaoSQLite.getDao();

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