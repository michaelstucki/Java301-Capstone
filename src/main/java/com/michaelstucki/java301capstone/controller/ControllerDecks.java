package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.dao.Dao;
import com.michaelstucki.java301capstone.dao.DaoSQLite;
import com.michaelstucki.java301capstone.dto.Deck;
import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.paint.Color;

import java.util.Map;

public class ControllerDecks {
    @FXML
    private MenuItem drill;
    @FXML
    private Label userMessage;
    @FXML
    private TextField deckName;
    @FXML
    private MenuItem open;
    @FXML
    private MenuItem delete;
    @FXML
    private ContextMenu itemContextMenu;
    @FXML
    private ListView<String> decksView;
    private SceneManager sceneManager;
    private Dao dao;

    public void init() {
         // Populate decksView with user's decks in database
         Map<String, Deck> decks = dao.getDecks();
         decksView.getItems().clear();
         decks.keySet().forEach(name -> decksView.getItems().add(name));
    }

    public void addDeck() {
        if (deckName.getText().trim().isEmpty()) {
            userMessage.setTextFill(Color.RED);
            userMessage.setText(deckName.getText() + " deck name not entered!");
        } else if (containsIgnoreCase(deckName.getText())) {
            userMessage.setTextFill(Color.RED);
            userMessage.setText(deckName.getText() + " already exists!");
        } else {
            Deck deck = new Deck(deckName.getText());
            dao.addDeck(deck);
            userMessage.setTextFill(Color.GREEN);
            userMessage.setText("deck added!");
            decksView.getItems().add(deckName.getText());
        }
        deckName.setText("");
    }

    private boolean containsIgnoreCase(String searchString) {
        return decksView.getItems().stream().anyMatch(item ->
                item.equalsIgnoreCase(searchString));
    }

    public void exitClick() {
        sceneManager.exit();
    }

    @FXML
    public void initialize() {
        dao = DaoSQLite.getDao();
        sceneManager = SceneManager.getScreenManager();
        decksView.setItems(FXCollections.observableArrayList());
        decksView.setContextMenu(itemContextMenu);

        // Open deck
        open.setOnAction(event -> {
            String selectedItem = decksView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                Deck deck = dao.getDeck(selectedItem);
                sceneManager.setSharedDeck(deck);
                sceneManager.showView("/fxml/cards.fxml");
            }
        });

        // Delete deck
        delete.setOnAction(event -> {
            String selectedItem = decksView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                dao.deleteDeck(selectedItem);
                decksView.getItems().remove(selectedItem);
            }
        });

        // Drill deck
        drill.setOnAction(event -> {
            String selectedItem = decksView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                Deck deck = dao.getDeck(selectedItem);
                sceneManager.setSharedDeck(deck);
                sceneManager.showView("/fxml/drills.fxml");
            }
        });

        // Rename deck (does not allow duplicate names)
        // remove selected deck from decks map & put new deck in its place
        // the deck's cards are unchanged, only the deck's name has changed
        // since map keys are immutable
        decksView.setEditable(true);
        decksView.setCellFactory(TextFieldListCell.forListView());
        decksView.setOnEditCommit(event -> {
            int index = event.getIndex();
            String oldName = event.getSource().getSelectionModel().getSelectedItem();
            String newName = event.getNewValue();
            if (newName != null && !newName.trim().isEmpty() && !containsIgnoreCase(newName)) {
                decksView.getItems().set(index, newName);
                dao.changeDeckName(oldName, newName);
            }
        });

        deckName.focusedProperty().addListener((observable, oldValue, newValue) -> {
            userMessage.setText("");
        });

        decksView.focusedProperty().addListener((observable, oldValue, newValue) -> {
            userMessage.setText("");
        });
    }
}
