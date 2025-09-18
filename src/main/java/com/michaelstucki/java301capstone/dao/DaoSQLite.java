package com.michaelstucki.java301capstone.dao;

import com.michaelstucki.java301capstone.dto.Deck;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import static com.michaelstucki.java301capstone.constants.Constants.databasePath;
import static com.michaelstucki.java301capstone.constants.Constants.usersTable;

public class DaoSQLite implements Dao {
    private static DaoSQLite DAO;
    private final Map<String, Deck> decks;

    private DaoSQLite() {
        decks = new HashMap<>();
        String command = "CREATE TABLE IF NOT EXISTS " + usersTable + " (id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        command += "userName VARCHAR(20) UNIQUE, password VARCHAR(20), securityAnswer VARCHAR(20));";
        createTable(command);
    }

    public static synchronized DaoSQLite getDao() {
        if (DAO == null) DAO = new DaoSQLite();
        return DAO;
    }

    @Override
    public void addDeck(Deck deck) { decks.put(deck.getName(), deck); }
    @Override
    public void deleteDeck(String deckName) { decks.remove(deckName); }
    @Override
    public Deck getDeck(String deckName) { return decks.get(deckName); }
    @Override
    public Map<String, Deck> getDecks() { return decks; }

    private void createTable(String command) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
            try (PreparedStatement stmt = connection.prepareStatement(command)) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    @Override
    public void addUser(String userName, String password, String securityAnswer) {
        String command = "INSERT INTO " + usersTable + " (userName, password, securityAnswer) VALUES ('";
        command += userName + "', '" + password + "', '" + securityAnswer + "');";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
            try (PreparedStatement stmt = connection.prepareStatement(command)) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    @Override
    public void getUser(String userName) {
        String command = "SELECT * FROM " + usersTable + " WHERE userName = '" + userName + "';";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
            try (PreparedStatement stmt = connection.prepareStatement(command)) {
               ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        System.out.println("Result: " + rs.getInt(1));
                        System.out.println("Result: " + rs.getString(2));
                        System.out.println("Result: " + rs.getString(3));
                        System.out.println("Result: " + rs.getString(4));
                    }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
