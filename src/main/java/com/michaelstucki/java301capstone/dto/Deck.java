package com.michaelstucki.java301capstone.dto;

import java.util.HashMap;
import java.util.Map;

public class Deck {
    private String name;
    private Map<Integer, Card> cards;
    private int numberOfCards;

    public Deck(String title) {
        this.name = title;
        cards = new HashMap<>();
    }

    public void addCard(Card card) {
        numberOfCards++;
        card.setId(numberOfCards);
        cards.put(numberOfCards, card);
    }

    public Card getCard(int id) { return cards.get(id); }

    public void deleteCard(Integer id) {
        cards.remove(id);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Map<Integer, Card> getCards() { return cards; }
    public void setCards(Map<Integer, Card> cards) { this.cards = cards; }

    @Override
    public String toString() { return name; }
}
