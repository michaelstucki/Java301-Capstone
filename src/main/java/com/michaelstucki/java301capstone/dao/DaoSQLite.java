package com.michaelstucki.java301capstone.dao;

import com.michaelstucki.java301capstone.dto.Card;
import com.michaelstucki.java301capstone.dto.Deck;
import com.michaelstucki.java301capstone.dto.User;
import java.sql.*;
import java.time.LocalDate;
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
                         "creation_date TEXT, reviewed_date TEXT, " +
                         "due_date TEXT, deck_id INTEGER, " +
                         "number_reviews INTEGER, number_passes INTEGER, " +
                         "FOREIGN KEY (deck_id) REFERENCES " + decksTable + " (deck_id) " +
                         "ON DELETE CASCADE);");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    @Override
    public void addUser(String userName, String password, String securityAnswer) {
        user = new User(userName, password, securityAnswer);
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
        String command = "SELECT * FROM " + usersTable + " WHERE userName = '" + userName + "';";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
            try (PreparedStatement stmt = connection.prepareStatement(command)) {
               ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        userName = rs.getString("username");
                        String password = rs.getString("password");
                        String securityAnswer = rs.getString("securityAnswer");
                        user = new User(userName, password, securityAnswer);
                    }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return user;
    }

    @Override
    public void changeUserPassword(String userName, String password) {
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

        // Get all cards for each user's deck
        for (Deck deck : decks.values()) {
            command = "SELECT * FROM cards c " +
                      "JOIN decks d ON d.deck_id = c.deck_id " +
                      "JOIN users u ON u.user_id = d.user_id " +
                      "WHERE u.username = '" + userName + "' AND d.name = '" + deck.getName() + "';";

            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
                try (PreparedStatement stmt = connection.prepareStatement(command)) {
                    ResultSet rs = stmt.executeQuery();
                    while(rs.next()) {
                        int cardId = rs.getInt("card_id");
                        String front = rs.getString("front");
                        String back = rs.getString("back");
                        int leitnerBox = rs.getInt("leitner_box");
                        String creationDate = rs.getString("creation_date");
                        String reviewedDate = rs.getString("reviewed_date");
                        String dueDate = rs.getString("due_date");
                        int numberOfReviews = rs.getInt("number_reviews");
                        int numberOfPasses = rs.getInt("number_passes");
                        Card card = new Card(cardId, front, back, creationDate, reviewedDate, dueDate, leitnerBox,
                                numberOfReviews, numberOfPasses);
                        deck.addCard(cardId, card);
                    }
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
        return decks;
    }

    @Override
    public Deck getDeck(String deckName) { return decks.get(deckName); }

    @Override
    public void changeDeckName(String oldName, String newName) {
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

    @Override
    public Card addCard(String front, String back, Deck deck) {
        String today = LocalDate.now().toString();
        int card_id = -1;
        String userName = user.getUsername();
        String deckName = deck.getName();
        String command = "INSERT INTO cards (front, back, leitner_box, creation_date, reviewed_date, due_date, " +
                         "deck_id, number_reviews, number_passes) " +
                         "VALUES ('" + front + "', '" + back + "', 0, '" + today + "', '" + today + "', '" + today + "'," +
                         "(SELECT deck_id FROM decks d JOIN users u ON d.user_id = u.user_id " +
                         "WHERE u.username = '" + userName + "' AND d.name = '" + deckName + "'), " +
                         "0, 0);";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
            try (PreparedStatement stmt = connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
                stmt.executeUpdate();
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                card_id = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        command = "SELECT * FROM cards WHERE card_id = '" + card_id + "';";

        Card card = null;
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
            try (PreparedStatement stmt = connection.prepareStatement(command)) {
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    int cardId = rs.getInt("card_id");
                    front = rs.getString("front");
                    back = rs.getString("back");
                    int leitnerBox = rs.getInt("leitner_box");
                    String creationDate = rs.getString("creation_date");
                    String reviewedDate = rs.getString("reviewed_date");
                    String dueDate = rs.getString("due_date");
                    int numberOfReviews = rs.getInt("number_reviews");
                    int numberOfPasses = rs.getInt("number_passes");
                    card = new Card(cardId, front, back, creationDate, reviewedDate, dueDate, leitnerBox,
                            numberOfReviews, numberOfPasses);
                    deck.addCard(cardId, card);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return card;
    }
}
