package com.michaelstucki.java301capstone.dao;

import com.michaelstucki.java301capstone.dto.Card;
import com.michaelstucki.java301capstone.dto.Deck;
import com.michaelstucki.java301capstone.dto.User;

import java.util.Map;

public interface Dao {
    public void addUser(String userName, String password, String securityAnswer);
    public User getUser(String userName);
    public void changeUserPassword(String userName, String password);
    public void deleteUser(String userName);

    public void addDeck(Deck deck);
    public Deck getDeck(String deckName);
    public Map<String, Deck> getDecks();
    public void changeDeckName(String oldName, String newName);
    public void deleteDeck(String deckName);

    public Card addCard(String front, String back, Deck deck);

}
