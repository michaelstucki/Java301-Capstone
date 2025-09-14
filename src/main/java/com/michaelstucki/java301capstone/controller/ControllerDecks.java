package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.paint.Color;

public class ControllerDecks {
    @FXML
    private Label userMessage;
    @FXML
    private TextField deckTitle;
    @FXML
    private MenuItem open;
    @FXML
    private MenuItem delete;
    @FXML
    private ContextMenu itemContextMenu;
    @FXML
    private ListView<String> decksView;
    private SceneManager sceneManager;
    private ObservableList<String> items;

    private boolean containsIgnoreCase(String searchString) {
        return decksView.getItems().stream().anyMatch(item -> item.equalsIgnoreCase(searchString));
    }

    public void open(ActionEvent event) {
        System.out.println("open deck");
        String selectedItem = decksView.getSelectionModel().getSelectedItem();
        System.out.println("selected: " + selectedItem);
    }

    public void addDeck() {
        String deckDummy = "A";
        if (deckTitle.getText().equalsIgnoreCase(deckDummy)) {
            userMessage.setTextFill(Color.RED);
            userMessage.setText(deckTitle.getText() + " already exists!");
        } else {
            userMessage.setTextFill(Color.GREEN);
            items.add(deckTitle.getText());
            userMessage.setText("Deck added!");
        }
        deckTitle.setText("");
    }

    public void exitClick() {
        sceneManager.exit();
    }
    @FXML
    public void initialize() {
        sceneManager = SceneManager.getScreenManager();

        items = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

        decksView.setItems(items);
        decksView.setContextMenu(itemContextMenu);

        // Delete action
        delete.setOnAction(event -> {
            String selectedItem = decksView.getSelectionModel().getSelectedItem();
            decksView.getItems().remove(selectedItem);
        });

        // Rename action (does not allow duplicate names)
        decksView.setEditable(true);
        decksView.setCellFactory(TextFieldListCell.forListView());
        decksView.setOnEditCommit(event -> {
            int index = event.getIndex();
            String newValue = event.getNewValue();
            if (!newValue.trim().isEmpty() && !containsIgnoreCase(newValue)) {
                decksView.getItems().set(index, newValue);
            }
        });

        deckTitle.focusedProperty().addListener((observable, oldValue, newValue) -> {
            userMessage.setText("");
        });

        decksView.focusedProperty().addListener((observable, oldValue, newValue) -> {
            userMessage.setText("");
        });

    }
}
