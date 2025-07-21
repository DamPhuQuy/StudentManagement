package com.mycompany.app.models;

import com.mycompany.app.utilities.Pair;
import java.time.LocalDate;
import java.util.HashMap;

public class Subjects {

    HashMap<String, Pair<LocalDate, Double>> subjects;

    public Subjects() {
        subjects = new HashMap<>();
    }

    public HashMap<String, Pair<LocalDate, Double>> getSubjectsTable() {
        return subjects;
    }

    public void setSubjectsTable(
        HashMap<String, Pair<LocalDate, Double>> subjectsTable
    ) {
        this.subjects = subjectsTable;
    }

    public void addSubject(
        String subjectName,
        LocalDate enrollDate,
        Double point
    ) {
        if (!subjects.containsKey(subjectName)) {
            subjects.put(subjectName, new Pair<>(enrollDate, point));
        } else {
            System.err.println("Subject already exists");
        }
    }

    public void removeSubject(String subjectName) {
        subjects.remove(subjectName);
    }
}
