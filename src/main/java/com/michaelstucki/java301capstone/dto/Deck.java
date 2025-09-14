package com.michaelstucki.java301capstone.dto;

import java.util.HashMap;
import java.util.Map;

public class Deck {
    private String title;
    private Map<Integer, Card> cards;

    public Deck(String title) {
        this.title = title;
        cards = new HashMap<>();
    }

    public void addCard(Card card) {
        cards.put(card.getId(), card);
    }

    public String getTitle() { return title; }

    @Override
    public String toString() { return title; }
}
