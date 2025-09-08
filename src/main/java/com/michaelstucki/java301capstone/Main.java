package com.michaelstucki.java301capstone;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/fonts/HerculanumLTProRoman.TTF"), 10);
        Font.loadFont(getClass().getResourceAsStream("/fonts/EBGaramond-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("/fonts/EBGaramond-Italic.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("/fonts/EBGaramond-Bold.ttf"), 10);
        SceneManager screenManager = SceneManager.getScreenManager();
        screenManager.setStage(stage);
        screenManager.showView("/fxml/login.fxml", "Home");
        screenManager.showView("/fxml/home.fxml", "Home");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}