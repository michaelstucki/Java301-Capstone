package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.dao.Dao;
import com.michaelstucki.java301capstone.dao.DaoSQLite;
import com.michaelstucki.java301capstone.dto.User;
import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.util.Objects;

public class ControllerDeleteAccount {
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
    private SceneManager sceneManager;
    private Dao dao;

    public void deleteAccountClick() {
        User user = dao.getCurrentUser();
        if (password.getText().isEmpty()) {
            userMessage.setText("password not entered!");
        } else if (!password.getText().equals(user.getPassword())) {
            userMessage.setText("invalid password!");
        } else if (passwordRetype.getText().isEmpty()) {
            userMessage.setText("password not entered!");
        } else if (!passwordRetype.getText().equals(password.getText())) {
            userMessage.setText("passwords do not match!");
        } else {
            dao.deleteUser(user.getUsername());
            userMessage.setTextFill(Color.GREEN);
            userMessage.setText("password changed!");
            clearInputs();
            sceneManager.showView("/fxml/home.fxml");
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

    private void clearInputs() {
        password.setText("");
        passwordRetype.setText("");
        userMessage.setText("");
        userMessage.setTextFill(Color.RED);
    }

    public void welcomeClick() {
        clearInputs();
        sceneManager.showView("/fxml/welcome.fxml");
    }

    public void exitClick() {
        sceneManager.exit();
    }

    public void initialize() {
        sceneManager = SceneManager.getScreenManager();
        dao = DaoSQLite.getDao();

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
