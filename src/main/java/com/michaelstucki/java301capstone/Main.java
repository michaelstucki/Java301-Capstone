package com.michaelstucki.java301capstone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final double GOLDEN_RATIO = 1.618;
        final double height = 650;
        final double width = height * GOLDEN_RATIO;
        Font.loadFont(getClass().getResourceAsStream("/fonts/HerculanumLTProRoman.TTF"), 10);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/landing.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}