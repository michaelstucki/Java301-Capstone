package com.michaelstucki.java301capstone.dao;

import com.michaelstucki.java301capstone.dto.Card;
import com.michaelstucki.java301capstone.dto.Deck;
import java.util.Map;

public interface Dao {
    void addDeck(Deck deck);
    void deleteDeck(String deckName);
    Deck getDeck(String deckName);
    Map<String, Deck> getDecks();

//    public void addUser(String userName, String password, String email);
//    public void getUser(String userName);
}
