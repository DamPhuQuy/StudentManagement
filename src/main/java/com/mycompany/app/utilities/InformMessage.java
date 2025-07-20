package com.mycompany.app.utilities;

public class InformMessage {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

    public static void error(String message) {
        System.out.println(RED + "[ERROR] " + message + RESET);
    }

    public static void success(String message) {
        System.out.println(GREEN + "[SUCCESS] " + message + RESET);
    }

    public static void warning(String message) {
        System.out.println(YELLOW + "[WARNING] " + message + RESET);
    }

    public static void info(String message) {
        System.out.println(BLUE + "[INFO] " + message + RESET);
    }
}
