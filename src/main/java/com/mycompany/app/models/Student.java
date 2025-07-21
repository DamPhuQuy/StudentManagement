package com.mycompany.app.models;

import com.mycompany.app.utilities.*;
import java.time.LocalDate;

public class Student {

    private Account account;
    private String fullname;
    private LocalDate dob;
    private Gender gender;
    private String email;
    private String address;
    private String phone;
    private String classroom;

    public Student() {}

    public Student(
        Account account,
        String fullname,
        LocalDate dob,
        Gender gender,
        String email,
        String address,
        String phone,
        String classroom
    ) {
        this.account = account;
        this.fullname = fullname;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.classroom = classroom;
    }

    public Account getAccount() {
        return account;
    }

    public String getFullname() {
        return fullname;
    }

    public String getDob() {
        return dob.format(Constants.DATE_FORMAT);
    }

    public Gender getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
