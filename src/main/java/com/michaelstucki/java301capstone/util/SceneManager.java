package com.michaelstucki.java301capstone.util;

import com.michaelstucki.java301capstone.Main;
import com.michaelstucki.java301capstone.constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class SceneManager {
    private Stage stage;
    private final Map<String, Scene> sceneCache = new HashMap<>();
    private final static SceneManager SCENE_MANAGER = new SceneManager();

    private SceneManager() {}

    public static SceneManager getScreenManager() { return SCENE_MANAGER; }

    public void setStage(Stage stage) { this.stage = stage; }

    public void showView(String fxmlPath, String title) {
        try {
            Scene scene = sceneCache.get(fxmlPath);
            if (scene == null) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlPath));
                scene = new Scene(fxmlLoader.load(), Constants.width, Constants.height);
                sceneCache.put(fxmlPath, scene);
                stage.setScene(scene);
            }
            stage.setScene(scene);
//            stage.setTitle(title);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
