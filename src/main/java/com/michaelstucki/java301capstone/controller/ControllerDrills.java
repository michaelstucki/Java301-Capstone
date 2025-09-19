package com.michaelstucki.java301capstone.controller;

import com.michaelstucki.java301capstone.dao.Dao;
import com.michaelstucki.java301capstone.dao.DaoSQLite;
import com.michaelstucki.java301capstone.dto.Card;
import com.michaelstucki.java301capstone.dto.Deck;
import com.michaelstucki.java301capstone.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private LocalDate today;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Dao dao;


    public void init(Deck sharedDeck) {
        today = LocalDate.now();
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

        // Put only the cards due today into a mutable list
        List<Card> valueList = deck.getCards().values().stream()
                .filter(card -> !LocalDate.parse(card.getDueDate(), dateTimeFormatter).isAfter(today))
                .collect(Collectors.toCollection(ArrayList::new));

        // Shuffle the list of cards (needs a mutable list)
        Collections.shuffle(valueList);

        // Add the list of cards to the queue
        queue.addAll(valueList);
    }

    public void startClick() {
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

    public void stopClick() {
        questionAnswer.clear();
        start.setDisable(false);
        stop.setDisable(true);
        next.setDisable(true);
        pass.setDisable(true);
        fail.setDisable(true);
    }

    private void updateCard(Card card, String passFail) {
        card.setNumberOfReviews(card.getNumberOfReviews() + 1);
        card.setReviewedDate(today.toString());
        switch (passFail) {
            case "pass":
                card.setNumberOfPasses(card.getNumberOfPasses() + 1);
                int leitnerBox = card.getLeitnerBox();
                leitnerBox++;
                card.setLeitnerBox(leitnerBox);
                long daysToAdd = (long) Math.pow(2.0, (double) leitnerBox);
                card.setDueDate(today.plusDays(daysToAdd).toString());
                break;
            case "fail":
                card.setLeitnerBox(0);
                card.setDueDate(today.plusDays(1).toString());
                break;
        }
        dao.updateCard(card);
    }

    public void passClick() {
        next.setDisable(false);
        pass.setDisable(true);
        fail.setDisable(true);
        Card card = queue.poll();
        updateCard(card, "pass");
        if (queue.isEmpty()) {
            next.setDisable(true);
            stop.setDisable(true);
            start.setDisable(false);
            questionAnswer.clear();
            drillOver.setVisible(true);
        }
    }

    public void failClick() {
        pass.setDisable(true);
        fail.setDisable(true);
        next.setDisable(false);
        Card card = queue.poll();
        queue.add(card);
        updateCard(card, "fail");
    }

    public void decksClick() {
        sceneManager.showView("/fxml/decks.fxml");
    }
    public void welcomeClick() { sceneManager.showView("/fxml/welcome.fxml"); }
    public void exitClick() {
        sceneManager.exit();
    }

    @FXML
    public void initialize() {
        sceneManager = SceneManager.getScreenManager();
        dao = DaoSQLite.getDao();

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
