package com.michaelstucki.java301capstone;

import com.michaelstucki.java301capstone.constants.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.Objects;
import javafx.stage.Stage;

public class Controller {
    public Hyperlink login;
    public Hyperlink createAccount;
    public Hyperlink forgotPassword;
    public Hyperlink home;
    public TextField username;
    public TextField password;
    public PasswordField passwordField;
    public ImageView eyeIcon;
    private final FXMLLoader loginRoot = new FXMLLoader(Main.class.getResource("/fxml/login.fxml"));
    private final FXMLLoader homeRoot = new FXMLLoader(Main.class.getResource("/fxml/home.fxml"));
    private boolean passwordVisible = false;

    public void homeClick(ActionEvent event) throws IOException {
        Hyperlink hyperlink = (Hyperlink) event.getSource();
        Stage stage = (Stage) hyperlink.getScene().getWindow();
        Scene scene = new Scene(homeRoot.load(), Constants.width, Constants.height);
        stage.setTitle("Home");
        stage.setScene(scene);
    }

    public void loginClick(ActionEvent event) throws IOException {
        Hyperlink hyperlink = (Hyperlink) event.getSource();
        Stage stage = (Stage) hyperlink.getScene().getWindow();
        Scene scene = new Scene(loginRoot.load(), Constants.width, Constants.height);
        stage.setTitle("Login");
        stage.setScene(scene);
    }

    public void signInClick() {
        System.out.println("on sign in click");
    }

    public void createAccountClick() {
        System.out.println("create account clicked");
    }

    public void forgotPasswordClick() {
        System.out.println("forgot password clicked");
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
}