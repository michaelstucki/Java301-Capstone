package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.dto.Deck;
import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ControllerDrills {
    @FXML
    private Button start;
    @FXML
    private Button stop;
    @FXML
    private Button pass;
    @FXML
    private Button next;
    @FXML
    private Button fail;
    @FXML
    private TextArea questionAnswer;
    @FXML
    private Label deckName;
    private SceneManager sceneManager;
    private Deck deck;
    private boolean isFront;

    // Demo items
    private final int totalIterations = 2;
    private int currentIteration = 0;

    public void init(Deck sharedDeck) {
        deck = sharedDeck;
        deckName.setText(deck.getName());
        start.setDisable(false);
        stop.setDisable(true);
        next.setDisable(true);
        pass.setDisable(true);
        fail.setDisable(true);

        // Demo
        currentIteration = 0;
    }

    public void startClick(ActionEvent event) {
        start.setDisable(true);
        stop.setDisable(false);
        next.setDisable(false);
    }

    public void stopClick(ActionEvent event) {
        init(deck);
    }

    public void passClick(ActionEvent event) {
        System.out.println("passClick");
        pass.setDisable(true);
        fail.setDisable(true);
    }

    public void failClick(ActionEvent event) {
        System.out.println("failClick");
        pass.setDisable(true);
        fail.setDisable(true);
    }

    public void decksClick() {
        sceneManager.showView("/fxml/decks.fxml");
    }

    public void exitClick() {
        sceneManager.exit();
    }

    @FXML
    public void initialize() {
        sceneManager = SceneManager.getScreenManager();
        questionAnswer.setEditable(false);

        // Demo items
        isFront = true;
        String front = "front";
        String back = "back";
        questionAnswer.setText(front);
        questionAnswer.setOnMouseClicked(event -> {
            questionAnswer.setText(isFront ? back : front);
            isFront = !isFront;
        });

        next.setOnAction(event -> {
            pass.setDisable(false);
            fail.setDisable(false);
            if (currentIteration < totalIterations) {
                System.out.println("currentIteration: " + currentIteration);
                currentIteration++;
                if (currentIteration == totalIterations) {
                    init(deck);
                }
            }
        });
    }
}
