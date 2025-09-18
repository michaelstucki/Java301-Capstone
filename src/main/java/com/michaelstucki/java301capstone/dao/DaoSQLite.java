package com.michaelstucki.java301capstone.dao;

import com.michaelstucki.java301capstone.dto.Deck;
import com.michaelstucki.java301capstone.dto.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static com.michaelstucki.java301capstone.constants.Constants.*;

public class DaoSQLite implements Dao {
    private static DaoSQLite DAO;
    private final Map<String, Deck> decks;
    private User user;

    private DaoSQLite() {
        decks = new HashMap<>();
        createTables();
    }

    public static synchronized DaoSQLite getDao() {
        if (DAO == null) DAO = new DaoSQLite();
        return DAO;
    }

    private void createTables() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
             Statement stmt = connection.createStatement()) {
                // Enable foreign key support
                stmt.execute("PRAGMA foreign_keys= ON;");

                // Create user table
                stmt.execute("CREATE TABLE IF NOT EXISTS " + usersTable +
                             " (user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                             "userName VARCHAR(20) UNIQUE, password VARCHAR(20), " +
                             " securityAnswer VARCHAR(20));");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    @Override
    public void addUser(String userName, String password, String securityAnswer) {
        System.out.println("addUser");
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
    public User getUser(String userName) {
        User user = null;
        System.out.println("getUser");
        String command = "SELECT * FROM " + usersTable + " WHERE userName = '" + userName + "';";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
            try (PreparedStatement stmt = connection.prepareStatement(command)) {
               ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        userName = rs.getString("username");
                        String password = rs.getString("password");
                        String securityAnswer = rs.getString("securityAnswer");
                        user = new User(userName, password, securityAnswer);

                        System.out.println("username: " + userName);
                        System.out.println("password: " + password);
                        System.out.println("securityAnswer :" + securityAnswer);
                    }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return user;
    }

    @Override
    public void changeUserPassword(String userName, String password) {
        System.out.println("changeUserPassword");
        String command = "UPDATE " + usersTable + " SET password = " + "'" + password + "'" + " WHERE username = " +
                         "'" + userName + "';";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
            try (PreparedStatement stmt = connection.prepareStatement(command)) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(String userName) {
        System.out.println("deleteUser");
        String command = "DELETE FROM " + usersTable + " WHERE username = " + "'" + userName + "';";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
            try (PreparedStatement stmt = connection.prepareStatement(command)) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    @Override
    public void addDeck(Deck deck) {
        decks.put(deck.getName(), deck);

    }


    @Override
    public void deleteDeck(String deckName) { decks.remove(deckName); }
    @Override
    public Deck getDeck(String deckName) { return decks.get(deckName); }
    @Override
    public Map<String, Deck> getDecks() { return decks; }

}
