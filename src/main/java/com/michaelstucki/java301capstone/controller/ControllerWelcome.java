package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.util.SceneManager;

public class ControllerWelcome {
    private SceneManager sceneManager;

    public void decksClick() { sceneManager.showView("/fxml/decks.fxml"); }
    public void changePasswordClick() { sceneManager.showView("/fxml/change_password.fxml"); }
    public void deleteAccountClick() { sceneManager.showView("/fxml/delete_account.fxml"); }
    public void logoutClick() { sceneManager.showView("/fxml/home.fxml"); }
    public void exitClick() {
        sceneManager.exit();
    }
    public void initialize() { sceneManager = SceneManager.getScreenManager();  }
}
