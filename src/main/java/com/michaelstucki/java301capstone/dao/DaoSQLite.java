package com.michaelstucki.java301capstone.dao;

import static com.michaelstucki.java301capstone.constants.Constants.*;

import com.michaelstucki.java301capstone.dto.Card;
import com.michaelstucki.java301capstone.dto.Deck;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DaoSQLite implements Dao {
    private final Map<String, Deck> decks;

    public DaoSQLite() {
        decks = new HashMap<>();
    }

    public void addDeck(Deck deck) { decks.put(deck.getName(), deck); }
    public void deleteDeck(String deckName) { decks.remove(deckName); }
    public Deck getDeck(String deckName) { return decks.get(deckName); }
    public Map<String, Deck> getDecks() { return decks; }


//    public void addUser(String userName, String password, String email) {
//        String command = "CREATE TABLE IF NOT EXISTS " + usersTable + " (id INTEGER PRIMARY KEY AUTOINCREMENT, ";
//        command += "userName VARCHAR(20) UNIQUE, password VARCHAR(20), email VARCHAR(20) UNIQUE);";
//        runCommand(command);
//        command = "INSERT INTO " + usersTable + " (userName, password, email) VALUES ('";
//        command += userName + "', '" + password + "', '" + email + "');";
//        runCommand(command);
//    }
//
//    public void getUser(String userName) {
//        String command = "SELECT * FROM " + usersTable + " WHERE userName = '" + userName + "';";
//        runCommand(command);
//    }
//
//    private void runCommand(String command) {
//        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
//            try (PreparedStatement stmt = connection.prepareStatement(command)) {
//                if (command.startsWith("SELECT")) {
//                    ResultSet rs = stmt.executeQuery();
//                    while (rs.next()) {
//                        System.out.println("Result: " + rs.getInt(1));
//                    }
//                } else {
//                    stmt.executeUpdate();
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Database error: " + e.getMessage());
//        }
//    }
}
