package com.mycompany.app.Controller;

import io.github.cdimascio.dotenv.Dotenv;

public class ReadENV {
    public ReadENV() {}

    private static final Dotenv dotenv = Dotenv.load(); // load file .env

    public static String getConnectionURL() {
        return dotenv.get("DB_URL");
    }

    public static String getUsername() {
        return dotenv.get("DB_USERNAME");
    }

    public static String getPassword() {
        return dotenv.get("DB_PASSWORD");
    }
}
