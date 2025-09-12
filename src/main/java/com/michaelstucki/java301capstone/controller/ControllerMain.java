package com.michaelstucki.java301capstone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ControllerMain {
    @FXML
    private ListView<String> decksView;

    @FXML
    public void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "Z"
                );
        decksView.setItems(items);
        decksView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Selected item:" + newValue);
            }
        });
    }
}
