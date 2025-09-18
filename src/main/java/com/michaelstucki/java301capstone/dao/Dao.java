package com.michaelstucki.java301capstone.dao;

import com.michaelstucki.java301capstone.dto.Deck;
import java.util.Map;

public interface Dao {
    public void getUser(String userName);
    public void addUser(String userName, String password, String securityAnswer);

    void addDeck(Deck deck);
    void deleteDeck(String deckName);
    Deck getDeck(String deckName);
    Map<String, Deck> getDecks();

//    public void addUser(String userName, String password, String email);
//    public void getUser(String userName);
}
