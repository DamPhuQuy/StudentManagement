package com.mycompany.app.models;

public class Enrollment {

    Student student;
    Subjects subjects;

    public Enrollment() {}

    public Enrollment(Student student, Subjects subjects) {
        this.student = student;
        this.subjects = subjects;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }
}
