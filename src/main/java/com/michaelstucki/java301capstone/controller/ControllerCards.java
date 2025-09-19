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
    private Button cancel;
    @FXML
    private Button save;
    @FXML
    private MenuItem add;
    @FXML
    private MenuItem edit;
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
    private ListView<String> cardsView;
    private SceneManager sceneManager;
    private Deck deck;
    private String saveMode;
    private int selectedCardId;

    public void init(Deck sharedDeck) {
        deck = sharedDeck;
        deckName.setText(deck.getName());
        front.clear();
        back.clear();
        cardsView.getItems().clear();
        deck.getCards().forEach((key, value) -> cardsView.getItems().add(value.toString()));
    }

    public void saveClick() {
        if (!front.getText().trim().isEmpty() && !back.getText().trim().isEmpty()) {
            Card card;
            switch (saveMode) {
                case "add":
                    card = new Card(front.getText(), back.getText());
                    deck.addCard(card);
                    cardsView.getItems().add(card.toString());
                    break;
                case "edit":
                    card = deck.getCard(selectedCardId);
                    card.setFront(front.getText());
                    card.setBack(back.getText());
                    init(deck);
                    break;
            }
        }
        front.setEditable(false);
        back.setEditable(false);
        save.setDisable(true);
        cancel.setDisable(true);
    }

    public void cancelClick(ActionEvent event) {
        front.clear();
        back.clear();
        front.setEditable(false);
        back.setEditable(false);
        save.setDisable(true);
        cancel.setDisable(true);
        cardsView.requestFocus();
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
        cardsView.setContextMenu(itemContextMenu);
        front.setEditable(false);
        back.setEditable(false);
        save.setDisable(true);
        cancel.setDisable(true);

        // Select card
        cardsView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    String[] tokens = newValue.split(cardToken);
                    String frontStr = tokens[1];
                    String backStr = tokens[2];
                    front.setText(frontStr);
                    back.setText(backStr);
                } else {
                    front.clear();
                    back.clear();
                }
            }
        });

        // Add card
        add.setOnAction(event -> {
            front.clear();
            back.clear();
            front.setEditable(true);
            back.setEditable(true);
            front.requestFocus();
            save.setDisable(false);
            cancel.setDisable(false);
            saveMode = "add";
        });

        // Edit card
        edit.setOnAction(event -> {
            String selectedItem = cardsView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String[] tokens = selectedItem.split(cardToken);
                selectedCardId = Integer.parseInt((tokens[0]));
                front.setEditable(true);
                back.setEditable(true);
                front.requestFocus();
                save.setDisable(false);
                cancel.setDisable(false);
                saveMode = "edit";
            }
        });

        // Delete card
        delete.setOnAction(event -> {
            String selectedItem = cardsView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String[] tokens = selectedItem.split(cardToken);
                int id = Integer.parseInt((tokens[0]));
                deck.deleteCard(id);
                cardsView.getItems().remove(selectedItem);
            }
        });
    }
}
