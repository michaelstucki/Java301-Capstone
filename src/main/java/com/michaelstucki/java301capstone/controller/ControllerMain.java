package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.util.Callback;

public class ControllerMain {
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
        decksView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                            setContextMenu(null);
                        } else {
                            setText(item);
                            setContextMenu(itemContextMenu);
                        }
                    }
                };
            }
        });
    }

    public void open(ActionEvent event) {
        System.out.println("open deck");
    }

    public void delete(ActionEvent event) {
        String selectedItem = decksView.getSelectionModel().getSelectedItem();
        decksView.getItems().remove(selectedItem);
    }

    public void exitClick() {
        sceneManager.exit();
    }
}
