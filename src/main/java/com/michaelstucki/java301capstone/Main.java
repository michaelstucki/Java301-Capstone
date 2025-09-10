package com.michaelstucki.java301capstone;

import com.michaelstucki.java301capstone.constants.Constants;
import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        for (String font : Constants.fonts) Font.loadFont(getClass().getResourceAsStream("/fonts/" + font), 10);
        SceneManager screenManager = SceneManager.getScreenManager();
        screenManager.setStage(stage);
        for (String fxml: Constants.fxmls) screenManager.showView("/fxml/" + fxml + ".fxml");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}