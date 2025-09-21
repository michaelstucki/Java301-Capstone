package com.michaelstucki.java301capstone.dao;

import com.michaelstucki.java301capstone.dto.Card;
import com.michaelstucki.java301capstone.dto.Deck;
import com.michaelstucki.java301capstone.dto.User;
import java.util.Map;

public interface Dao {
    void copyDatabase();

    void addUser(String userName, String password, String securityAnswer);
    User getUser(String userName);
    User getCurrentUser();
    void changeUserPassword(String userName, String password);
    void deleteUser(String userName);

    void addDeck(Deck deck);
    Deck getDeck(String deckName);
    Map<String, Deck> getDecks();
    void changeDeckName(String oldName, String newName);
    void deleteDeck(String deckName);

    Card addCard(String front, String back, Deck deck);
    void updateCard(Card card);
    void deleteCard(int cardId);

    void clearDecks();
}

