package com.michaelstucki.java301capstone.dao;

import com.michaelstucki.java301capstone.dto.Deck;
import com.michaelstucki.java301capstone.dto.User;

import java.util.Map;

public interface Dao {
    public User getUser(String userName);
    public void addUser(String userName, String password, String securityAnswer);
    public void changeUserPassword(String userName, String password);
    public void deleteUser(String userName);

    void addDeck(Deck deck);
    void deleteDeck(String deckName);
    Deck getDeck(String deckName);
    Map<String, Deck> getDecks();

//    public void addUser(String userName, String password, String email);
//    public void getUser(String userName);
}
