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
        for (String f : Constants.fonts) Font.loadFont(getClass().getResourceAsStream("/fonts/" + f), 10);
        SceneManager screenManager = SceneManager.getScreenManager();
        screenManager.setStage(stage);
        for (String f: Constants.fxmls) screenManager.showView("/fxml/" + f + ".fxml", f);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}