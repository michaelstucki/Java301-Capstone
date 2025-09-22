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

/**
 * Delete Account UI Controller
 * @author Michael Stucki
 * @version 1.0
 * @since 2025-09-21
 */
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

    /**
     * Delete Account button onAction
     */
    public void deleteAccountClick() {
        // Validate user inputs (does not allow users to share names)
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

    /**
     * Toggle password visibility
     */
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

    /**
     * Welcome hyperlink onAction (goes to Welcome UI)
     */
    public void welcomeClick() {
        clearInputs();
        sceneManager.showView("/fxml/welcome.fxml");
    }

    /**
     * Exit app
     */
    public void exitClick() { sceneManager.exit(); }

    /**
     * Initialize UI widgets and event handlers
     */
    @FXML
    public void initialize() {
        sceneManager = SceneManager.getScreenManager();
        // Get reference to DaoSQLite singleton (used to update model & database)
        dao = DaoSQLite.getDao();

        // Bidirectionally bind password inputs (so a change in one is reflected in the other)
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
