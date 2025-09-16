package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.dto.Card;
import com.michaelstucki.java301capstone.dto.Deck;
import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import java.awt.*;
import java.time.LocalTime;
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
    private boolean isDrillOver;

    public void init(Deck sharedDeck) {
        if (isDrillOver) {
            drillOver.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(event -> {
                drillOver.setVisible(false);
            });
            System.out.println(LocalTime.now());
            pause.play();
            System.out.println(LocalTime.now());
            isDrillOver = false;
        }
        deck = sharedDeck;
        deckName.setText(deck.getName());
        start.setDisable(false);
        stop.setDisable(true);
        next.setDisable(true);
        pass.setDisable(true);
        fail.setDisable(true);
        isFront = true;
        questionAnswer.clear();
        drillOver.setVisible(false);
        queue.clear();
    }

    private void setupQueue() {
        // Demo
        queue.add(new Card("1 + 1", "2"));
        queue.add(new Card("1 + 2", "3"));
    }

    public void startClick(ActionEvent event) {//        init(deck);
        setupQueue();
        start.setDisable(true);
        stop.setDisable(false);
        next.setDisable(false);
    }

    public void stopClick(ActionEvent event) {
        init(deck);
    }

    public void passClick(ActionEvent event) {
        Card card = queue.poll();
        if (queue.isEmpty()) {
            isDrillOver = true;
            init(deck);
        } else {
            pass.setDisable(true);
            fail.setDisable(true);
            next.setDisable(false);
        }
    }

    public void failClick(ActionEvent event) {
        Card card = queue.poll();
        queue.add(card);
        pass.setDisable(true);
        fail.setDisable(true);
        next.setDisable(false);
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
            } else {
                init(deck);
            }
        });
    }
}
