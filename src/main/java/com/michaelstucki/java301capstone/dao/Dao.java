package com.michaelstucki.java301capstone.dao;

import com.michaelstucki.java301capstone.dto.Deck;

public interface Dao {
    public void addDeck(Deck deck);
    public void addUser(String userName, String password, String email);
    public void getUser(String userName);
}
