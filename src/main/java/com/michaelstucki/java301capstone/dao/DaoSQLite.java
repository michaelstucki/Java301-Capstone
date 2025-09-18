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

                // Create users table
                stmt.execute("CREATE TABLE IF NOT EXISTS " + usersTable +
                             " (user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                             "userName VARCHAR(20) UNIQUE, password VARCHAR(20), " +
                             " securityAnswer VARCHAR(20));");

                // Create decks table
                stmt.execute("CREATE TABLE IF NOT EXISTS " + decksTable +
                             " (deck_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                             "name VARCHAR(40), user_id INTEGER, " +
                             "FOREIGN KEY (user_id) REFERENCES " + usersTable + " (user_id) " +
                             "ON DELETE CASCADE);");

                // Create cards table
            stmt.execute("CREATE TABLE IF NOT EXISTS " + cardsTable +
                         " (card_id INTEGER PRIMARY KEY, " +
                         "front TEXT, back TEXT, leitner_box INTEGER, " +
                         "creation_date DATE, reviewed_date DATE, due_date DATE, deck_id INTEGER, " +
                         "number_reviews INTEGER, number_passes INTEGER, " +
                         "FOREIGN KEY (deck_id) REFERENCES " + decksTable + " (deck_id) " +
                         "ON DELETE CASCADE);");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    @Override
    public void addUser(String userName, String password, String securityAnswer) {
        System.out.println("addUser");
        String command = "INSERT INTO " + usersTable + " (userName, password, securityAnswer) VALUES ('" +
                          userName + "', '" + password + "', '" + securityAnswer + "');";

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
        user = null;
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
        String command = "UPDATE " + usersTable + " SET password = " + "'" + password + "'" +
                         " WHERE username = '" + userName + "';";

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
        System.out.println("addDeck");
        // Update model
        String deckName = deck.getName();
        decks.put(deckName, deck);
        // Update database
        String userName = user.getUsername();
        String command =  "INSERT INTO decks (name, user_id) VALUES (" + "'" + deckName + "'," +
                          "(SELECT user_id FROM users WHERE username = " +  "'" + userName + "'));";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
            try (PreparedStatement stmt = connection.prepareStatement(command)) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    @Override
    public Map<String, Deck> getDecks() {
        System.out.println("getDecks");
        // Get all user's decks from database
        String userName = user.getUsername();
        String command = "SELECT * FROM decks d " +
                         "JOIN users u on d.user_id = u.user_id " +
                         "WHERE username = '" + userName + "';";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
            try (PreparedStatement stmt = connection.prepareStatement(command)) {
                ResultSet rs = stmt.executeQuery();
                // Instantiate a deck for each database deck
                while (rs.next()) {
                    String deckName = rs.getString("name");
                    Deck deck = new Deck(deckName);
                    decks.put(deckName, deck);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return decks;
    }

    @Override
    public Deck getDeck(String deckName) { return decks.get(deckName); }

    @Override
    public void changeDeckName(String oldName, String newName) {
        System.out.println("changeDeckName");
        // Update model
        // decks is a map, its key is the deck's title
        // so, to change the deck's title, the deck must be replaced
        Deck oldDeck = decks.remove(oldName);
        Deck newDeck = new Deck(newName);
        newDeck.setCards(oldDeck.getCards());
        decks.put(newName, newDeck);
        // Update database
        String userName = user.getUsername();
        String command = "UPDATE decks SET name = '" + newName + "' " +
                         "WHERE user_id = (SELECT user_id FROM users WHERE username = '" + userName +
                         "') AND name = '" + oldName + "';";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
            try (PreparedStatement stmt = connection.prepareStatement(command)) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    @Override
    public void deleteDeck(String deckName) {
        System.out.println("addDeck");
        // Update model
        decks.remove(deckName);
        // Update database
        String userName = user.getUsername();
        String command = "DELETE FROM decks WHERE user_id = (SELECT user_id from users " +
        "WHERE username = '" + userName + "') AND name = '" + deckName + "';";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
            try (PreparedStatement stmt = connection.prepareStatement(command)) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }



}
