package com.michaelstucki.java301capstone;

import com.michaelstucki.java301capstone.constants.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Constants.width, Constants.height);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}