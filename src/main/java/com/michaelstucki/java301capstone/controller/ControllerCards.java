package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.dto.Card;
import com.michaelstucki.java301capstone.dto.Deck;
import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class ControllerCards {
    @FXML
    private Label deckName;
    @FXML
    private TextArea front;
    @FXML
    private TextArea back;
    @FXML
    private Label userMessage;
    @FXML
    private ListView<String> cardsView;
    private SceneManager sceneManager;
    private Deck deck;

    public void init(Deck sharedDeck) {
        deck = sharedDeck;
        deckName.setText(deck.getName());
        cardsView.getItems().clear();
        deck.getCards().forEach((key, value) -> cardsView.getItems().add(value.toString()));
    }

    public void newClick(ActionEvent event) {
        if (!front.getText().trim().isEmpty() && !back.getText().trim().isEmpty()) {
            Card card = new Card(front.getText(), back.getText());
            deck.addCard(card);
            cardsView.getItems().add(card.toString());
        }
    }

    public void cancelClick(ActionEvent event) {
        System.out.println("cancelClick");
    }

    public void editClick(ActionEvent event) {
        System.out.println("editClick");
    }

    public void decksClick() {
        sceneManager.setSharedDeck(deck);
        sceneManager.showView("/fxml/decks.fxml");
    }

    public void exitClick() {
        sceneManager.exit();
    }

    @FXML
    public void initialize() {
        sceneManager = SceneManager.getScreenManager();
//        deckTitle.focusedProperty().addListener((observable, oldValue, newValue) -> {
//            userMessage.setText("");
//        });
//
//        decksView.focusedProperty().addListener((observable, oldValue, newValue) -> {
//            userMessage.setText("");
//        });
//
    }
}
