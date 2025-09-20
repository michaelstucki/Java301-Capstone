package com.michaelstucki.java301capstone.util;

import com.michaelstucki.java301capstone.Main;
import static com.michaelstucki.java301capstone.constants.Constants.*;
import com.michaelstucki.java301capstone.controller.ControllerCards;
import com.michaelstucki.java301capstone.controller.ControllerDecks;
import com.michaelstucki.java301capstone.controller.ControllerDrills;
import com.michaelstucki.java301capstone.controller.ControllerHome;
import com.michaelstucki.java301capstone.dto.Deck;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class SceneManager {
    private Stage stage;
    private final Map<String, Scene> sceneCache = new HashMap<>();
    private final Map<String, FXMLLoader> loaderCache = new HashMap<>();
    private final static SceneManager SCENE_MANAGER = new SceneManager();
    private Deck sharedDeck = new Deck("");

    private SceneManager() {}
    public static SceneManager getScreenManager() { return SCENE_MANAGER; }
    public void setStage(Stage stage) { this.stage = stage; }
    public void setSharedDeck(Deck sharedDeck) { this.sharedDeck = sharedDeck; }

    public void showView(String fxmlPath) {
        try {
            if (sceneCache.containsKey(fxmlPath)) {
                FXMLLoader loader = loaderCache.get(fxmlPath);
                // invoke controller init() only if FXMLLoader has been run
                if (fxmlPath.contains("cards")) {
                    ControllerCards controller = loader.getController();
                    controller.init(sharedDeck);
                } else if (fxmlPath.contains("drills")) {
                    ControllerDrills controller = loader.getController();
                    controller.init(sharedDeck);
                } else if (fxmlPath.contains("decks")) {
                    ControllerDecks controller = loader.getController();
                    controller.init();
                } else if (fxmlPath.contains("home")) {
                    ControllerHome controller = loader.getController();
                    controller.init();
                }
                Scene scene = sceneCache.get(fxmlPath);
                stage.setScene(scene);
            } else {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
                Scene scene = new Scene(loader.load(), width, height);
                sceneCache.put(fxmlPath, scene);
                loaderCache.put(fxmlPath, loader);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void exit() { stage.close(); }
}
