package com.mycompany.app.utilities.constants;

import java.time.format.DateTimeFormatter;

public class Constants {
    /**
     * Private constructor to prevent instantiation of this utility class.
     * This class is intended to be used statically.
     */
    private Constants() {
        // Prevent instantiation
        throw new UnsupportedOperationException("Constants class cannot be instantiated.");
    }

    public static final DateTimeFormatter DATE_FORMAT_INPUT =
        DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_FORMAT_OUTPUT =
        DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final String ACCOUNTS_PATH = "data/accounts.csv";
    public static final String STUDENTS_PATH = "data/students.csv";
    public static final String TEACHERS_PATH = "data/teachers.csv";
    public static final String SUBJECTS_PATH = "data/subjects.csv";
}
