package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.dto.Card;
import com.michaelstucki.java301capstone.dto.Deck;
import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.util.ArrayDeque;
import java.util.Queue;

public class ControllerDrills {
    @FXML
    private Label drillOver;
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
    private String front;
    private String back;
    private Queue<Card> queue;

    public void init(Deck sharedDeck) {
        deck = sharedDeck;
        deckName.setText(deck.getName());
        start.setDisable(false);
        stop.setDisable(true);
        next.setDisable(true);
        pass.setDisable(true);
        fail.setDisable(true);
    }

    private void setupQueue() {
        // Demo
        queue.clear();
        queue.add(new Card("1 + 1", "2"));
        queue.add(new Card("1 + 2", "3"));
        queue.add(new Card("1 + 3", "4"));
    }

    public void startClick(ActionEvent event) {//        init(deck);
        drillOver.setVisible(false);
        isFront = true;
        questionAnswer.clear();
        start.setDisable(true);
        stop.setDisable(false);
        next.setDisable(false);
        pass.setDisable(true);
        fail.setDisable(true);
        setupQueue();
    }

    public void stopClick(ActionEvent event) {
        questionAnswer.clear();
        start.setDisable(false);
        stop.setDisable(true);
        next.setDisable(true);
        pass.setDisable(true);
        fail.setDisable(true);
    }

    public void passClick(ActionEvent event) {
        pass.setDisable(true);
        fail.setDisable(true);
        next.setDisable(false);
        Card card = queue.poll();
        if (queue.isEmpty()) {
            next.setDisable(true);
            stop.setDisable(true);
            pass.setDisable(true);
            fail.setDisable(true);
            start.setDisable(false);
            questionAnswer.clear();
            drillOver.setVisible(true);
        }
    }

    public void failClick(ActionEvent event) {
        pass.setDisable(true);
        fail.setDisable(true);
        next.setDisable(false);
        if (!queue.isEmpty()) {
            Card card = queue.poll();
            queue.add(card);
        }
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
        drillOver.setVisible(false);
        queue = new ArrayDeque<>();

        questionAnswer.setText(front);
        questionAnswer.setOnMouseClicked(event -> {
            questionAnswer.setText(isFront ? back : front);
            isFront = !isFront;
        });

        next.setOnAction(event -> {
            next.setDisable(true);
            pass.setDisable(false);
            fail.setDisable(false);
            if (queue.peek() != null) {
                Card card = queue.peek();
                front = card.getFront();
                back = card.getBack();
                questionAnswer.setText(front);
            }
        });
    }
}
