package com.mycompany.app.services;

import java.time.LocalDate;
import java.util.HashMap;

import com.mycompany.app.models.Enrollment;
import com.mycompany.app.models.Student;
import com.mycompany.app.models.Subjects;
import com.mycompany.app.utilities.animation.Effect;
import com.mycompany.app.utilities.constants.Constants;
import com.mycompany.app.utilities.constants.Role;
import com.mycompany.app.utilities.helpers.Menu;
import com.mycompany.app.utilities.helpers.Pair;
import com.mycompany.app.utilities.io.InformMessage;
import com.mycompany.app.utilities.io.ProfileViewer;

public class StudentService implements Menu {

    HashMap<String, Enrollment> studentsMap;

    public StudentService() {
        studentsMap = new HashMap<>();
    }

    public StudentService(HashMap<String, Enrollment> studentsMap) {
        this.studentsMap = studentsMap;
    }

    public HashMap<String, Enrollment> getStudentsMap() {
        return studentsMap;
    }

    public void setStudentsMap(HashMap<String, Enrollment> studentsMap) {
        this.studentsMap = studentsMap;
    }

    public void addStudent(Student newStudent, Subjects subjects) {
        if (studentsMap.containsKey(newStudent.getAccount().getUsername())) {
            InformMessage.error("Student already exists");
            return;
        }
        Enrollment enrollment = new Enrollment(newStudent, subjects);
        studentsMap.put(newStudent.getAccount().getUsername(), enrollment);
    }

    public void removeStudent(Student student) {
        if (student == null) {
            InformMessage.error("Student not found");
            return;
        }
        studentsMap.remove(student.getAccount().getUsername());
    }

    public void updateStudent(Student student) {
        Enrollment enrollment = studentsMap.get(
            student.getAccount().getUsername()
        );
        if (enrollment != null) {
            enrollment.setStudent(student);
        }
    }

    public void updateUsername(
        String oldUsername,
        String newUsername,
        Student student
    ) {
        Enrollment enrollment = studentsMap.get(oldUsername);
        if (enrollment != null) {
            student.getAccount().setUsername(newUsername);
            enrollment.setStudent(student);
            studentsMap.put(newUsername, enrollment);
            studentsMap.remove(oldUsername);
            InformMessage.success("Username updated successfully");
        } else {
            InformMessage.error("Student not found");
        }
    }

    public void viewProfile(Student student) {
        Effect.progressBar();

        if (studentsMap.containsKey(student.getAccount().getUsername())) {
            System.out.println("Student Profile:");
            System.out.println("Name: " + student.getFullname());
            ProfileViewer.viewBasicProfile(student);
            System.out.println("Class: " + student.getClassName());
        } else {
            InformMessage.error("Student not found");
        }
    }

    public void viewEnrollment(Student student) {
        Effect.progressBar();

        if (studentsMap.containsKey(student.getAccount().getUsername())) {
            Enrollment enrollment = studentsMap.get(
                student.getAccount().getUsername()
            );

            if (enrollment != null) {
                System.out.println("Enrollment Details:");
                System.out.println("Student: " + student.getFullname());
                System.out.println("className: " + student.getClassName());

                HashMap<String, Pair<LocalDate, Double>> subjects = enrollment
                    .getSubjects()
                    .getSubjectsTable();
                for (String subjectName : subjects.keySet()) {
                    String d = subjects
                        .get(subjectName)
                        .getFirst()
                        .format(Constants.DATE_FORMAT_OUTPUT);
                    Double grade = subjects.get(subjectName).getSecond();

                    System.out.println(
                        "Subject: " +
                        subjectName +
                        " - Grade: " +
                        grade +
                        " - DateEnrolled: " +
                        d
                    );
                }
            } else {
                InformMessage.error("No enrollment found");
            }
        } else {
            InformMessage.error("Student not found");
        }
    }

    public double getGpa(Student student) {
        String username = student.getAccount().getUsername();
        if (studentsMap.containsKey(username)) {
            Enrollment enrollment = studentsMap.get(username);
            if (enrollment != null) {
                HashMap<String, Pair<LocalDate, Double>> subjects = enrollment
                    .getSubjects()
                    .getSubjectsTable();

                double totalPoints = 0;
                for (String subject : subjects.keySet()) {
                    Double grade = subjects.get(subject).getSecond();
                    totalPoints += grade;
                }

                return totalPoints / subjects.size();
            } else {
                InformMessage.error("No enrollment found");
                return 0.0;
            }
        } else {
            InformMessage.error("Student not found");
            return 0.0;
        }
    }

    @Override
    public void showMenu(Role role) {
        System.out.println("-----MENU-----"); 
        System.out.println("Welcome " + role.toString()); 
        System.out.println("1. View Profile"); 
        System.out.println("3. View Enrollment"); 
        System.out.println("4. Update Information"); 
        System.out.println("0. Back to menu"); 
    }   
}
