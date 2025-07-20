package com.mycompany.app.utilities;

import java.time.format.DateTimeFormatter;

public class Constants {

    public static final DateTimeFormatter DATE_FORMAT =
        DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final String ACCOUNTS_PATH = "data/accounts.csv";
    public static final String STUDENTS_PATH = "data/students.csv";
    public static final String TEACHERS_PATH = "data/teachers.csv";
    public static final String SUBJECTS_PATH = "data/subjects.csv";
}
