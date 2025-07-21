package com.mycompany.app.utilities.io;

import java.time.LocalDate;
import java.util.Map;

import com.mycompany.app.models.Enrollment;
import com.mycompany.app.models.PersonProfile;
import com.mycompany.app.models.Student;
import com.mycompany.app.utilities.helpers.Pair;

public class ProfileViewer {

    public ProfileViewer() {}

    public static void viewBasicProfile(PersonProfile person) {
        System.out.println("Name: " + person.getFullname());
        System.out.println("Day of birth: " + person.getDob());
        System.out.println("Gender: " + person.getGender());
        System.out.println("Email: " + person.getEmail());
        System.out.println("Address: " + person.getAddress());
        System.out.println("Phone Number: " + person.getPhone());
    }

    public static void printEnrollment(Student student, Enrollment enrollment) {
       System.out.println("Enrollment Details:");
       System.out.println("Student: " + student.getFullname());
       System.out.println("Classroom: " + student.getClassroom());

       var subjects = enrollment.getSubjects().getSubjectsTable();
       for (Map.Entry<String, Pair<LocalDate, Double>> entry : subjects.entrySet()) {
           String subjectName = entry.getKey();
           LocalDate date = entry.getValue().getFirst();
           Double grade = entry.getValue().getSecond();

           System.out.println("Subject: " + subjectName
                            + " - Grade: " + grade
                            + " - DateEnrolled: " + date.format(Constants.DATE_FORMAT));
       }
    }
}
