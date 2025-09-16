package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.dto.Deck;
import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ControllerDrills {
    @FXML
    private TextArea questionAnswer;
    @FXML
    private Label deckName;
    private SceneManager sceneManager;
    private Deck deck;
    private boolean isFront;

    public void init(Deck sharedDeck) {
        deck = sharedDeck;
        deckName.setText(deck.getName());
    }

    private void drill() {
    }

    public void passClick(ActionEvent event) {
        System.out.println("passClick");
    }

    public void failClick(ActionEvent event) {
        System.out.println("failClick");
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
        isFront = true;
        String front = "front";
        String back = "back";
        questionAnswer.setText(front);

        questionAnswer.setOnMouseClicked(event -> {
            questionAnswer.setText(isFront ? back : front);
            isFront = !isFront;
        });
    }
}
