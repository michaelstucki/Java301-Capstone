package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.TextFieldListCell;

public class ControllerMain {
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

    @FXML
    public void initialize() {
        sceneManager = SceneManager.getScreenManager();

        items = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "Z"
                );

        decksView.setItems(items);
        decksView.setContextMenu(itemContextMenu);
        delete.setOnAction(event -> {
                    String selectedItem = decksView.getSelectionModel().getSelectedItem();
                    decksView.getItems().remove(selectedItem);
                });

        decksView.setEditable(true);
        decksView.setCellFactory(TextFieldListCell.forListView());
        decksView.setOnEditCommit(event -> {
            System.out.println("setOnEditCommit...");
            int index = event.getIndex();
            String newValue = event.getNewValue();
            if (!decksView.getItems().contains(newValue)) {
                decksView.getItems().set(index, newValue);
            }
        });
    }

    public void open(ActionEvent event) {
        System.out.println("open deck");
        String selectedItem = decksView.getSelectionModel().getSelectedItem();
        System.out.println("selected: " + selectedItem);
    }

    public void exitClick() {
        sceneManager.exit();
    }
}
