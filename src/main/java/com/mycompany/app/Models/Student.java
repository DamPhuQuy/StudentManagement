package com.mycompany.app.Models;

public class Student {
    private int student_id;
    private String firstname;
    private String lastname;
    private String day_of_birth;
    private String phone;
    private String address;
    private String className;
    private Subjects subject;

    public Student(int student_id,
                   String firstname,
                   String lastname,
                   String day_of_birth,
                   String phone,
                   String address,
                   String className) {
        this.student_id = student_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.day_of_birth = day_of_birth;
        this.phone = phone;
        this.address = address;
        this.className = className;
        this.subject = new Subjects();
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDay_of_birth() {
        return day_of_birth;
    }

    public void setDay_of_birth(String day_of_birth) {
        this.day_of_birth = day_of_birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }
}
