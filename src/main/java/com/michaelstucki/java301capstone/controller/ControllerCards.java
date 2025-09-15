package com.michaelstucki.java301capstone.controller;

import static com.michaelstucki.java301capstone.constants.Constants.*;
import com.michaelstucki.java301capstone.dto.Card;
import com.michaelstucki.java301capstone.dto.Deck;
import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ControllerCards {
    @FXML
    private MenuItem delete;
    @FXML
    private ContextMenu itemContextMenu;
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
            cardsView.getItems().add(Integer.toString(card.getId()) + cardToken + card.toString());
            front.clear();
            back.clear();
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
        cardsView.setContextMenu(itemContextMenu);

        // Select card
        cardsView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String[] tokens = newValue.split(cardToken);
                String frontStr = tokens[1];
                String backStr = tokens[2];
                front.setText(frontStr);
                back.setText(backStr);
            }
        });

        // Delete card
        delete.setOnAction(event -> {
            String selectedItem = cardsView.getSelectionModel().getSelectedItem();
            String[] tokens = selectedItem.split(cardToken);
            int id = Integer.parseInt((tokens[0]));
            deck.deleteCard(id);
            cardsView.getItems().remove(selectedItem);
        });

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
