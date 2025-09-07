package com.michaelstucki.java301capstone.constants;

public final class Constants {
    private static final double GOLDEN_RATIO = 1.618;
    public static final double height = 650;
    public static final double width = height * GOLDEN_RATIO;
    public static final String databasePath = "src/main/resources/database/test.db";
    public static final String usersTable = "users";
    private Constants() {}
}
