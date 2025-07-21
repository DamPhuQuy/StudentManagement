package com.mycompany.app.models;

import com.mycompany.app.utilities.*;
import java.time.LocalDate;
import java.util.List;

public class Teacher {

    private String fullname;
    private LocalDate dob;
    private Gender gender;
    private String email;
    private String phone;
    private String address;
    private List<String> courses;
    private String level;
    private Account account;

    public Teacher() {}

    public Teacher(
        String fullname,
        LocalDate dob,
        Gender gender,
        String email,
        String phone,
        String address,
        List<String> courses,
        String level,
        Account account
    ) {
        this.fullname = fullname;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.courses = courses;
        this.level = level;
        this.account = account;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
