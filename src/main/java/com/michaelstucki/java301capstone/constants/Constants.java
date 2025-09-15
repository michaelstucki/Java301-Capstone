package com.michaelstucki.java301capstone.constants;

import com.michaelstucki.java301capstone.util.SceneManager;

public final class Constants {
    private static final double ASPECT_RATIO = 1.1;
    public static final double height = 750;
    public static final double width = height * ASPECT_RATIO;
    public static final String databasePath = "src/main/resources/database/test.db";
    public static final String usersTable = "users";
    public static final String[] fonts = {"HerculanumLTProRoman.TTF", "EBGaramond-Regular.ttf",
            "EBGaramond-Italic.ttf", "EBGaramond-Bold.ttf"};
    public static final String[] fxmls = {"cards", "decks", "forgot_password", "create_account", "home"};
    private Constants() {}
}
