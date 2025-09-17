package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.dto.Card;
import com.michaelstucki.java301capstone.dto.Deck;
import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
        drillOver.setVisible(false);
        start.setDisable(false);
        stop.setDisable(true);
        next.setDisable(true);
        pass.setDisable(true);
        fail.setDisable(true);
    }

    private void setupQueue() {
        queue.clear();

        // Demo cards
        Card c1 = new Card("q1", "a1");
        Card c2 = new Card("q2", "a2");
        Card c3 = new Card("q3", "a3");
        deck.addCard(c1);
        deck.addCard(c2);
        deck.addCard(c3);
        // Demo today date
        LocalDate today = LocalDate.of(2025, 9, 20);

        // Get only cards due today into a mutable list
        List<Card> valueList = deck.getCards().values().stream()
                .filter(card -> !card.getDueDate().isAfter(today))
                .collect(Collectors.toCollection(ArrayList::new));

        // Shuffle the list of cards
        Collections.shuffle(valueList);

        // Add the list of cards to the queue
        queue.addAll(valueList);
    }

    public void startClick(ActionEvent event) {//        init(deck);
        drillOver.setVisible(false);
        drillOver.setText("Excellent work!");
        isFront = true;
        questionAnswer.clear();

        setupQueue();
        if (queue.isEmpty()) {
            drillOver.setText("No cards are due!");
            drillOver.setVisible(true);
            start.setDisable(true);
        } else {
            start.setDisable(true);
            stop.setDisable(false);
            next.setDisable(false);
            pass.setDisable(true);
            fail.setDisable(true);
        }
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
        next.setDisable(false);
        pass.setDisable(true);
        fail.setDisable(true);
        Card card = queue.poll();
        if (queue.isEmpty()) {
            next.setDisable(true);
            stop.setDisable(true);
            start.setDisable(false);
            questionAnswer.clear();
            drillOver.setVisible(true);
        }
    }

    public void failClick(ActionEvent event) {
        pass.setDisable(true);
        fail.setDisable(true);
        next.setDisable(false);
        Card card = queue.poll();
        queue.add(card);
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
            if (queue.peek() != null) {
                Card card = queue.peek();
                front = card.getFront();
                back = card.getBack();
                questionAnswer.setText(front);
                pass.setDisable(false);
                fail.setDisable(false);
            }
        });
    }
}
