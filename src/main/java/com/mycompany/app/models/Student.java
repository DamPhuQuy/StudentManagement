package com.mycompany.app.models;

import com.mycompany.app.utilities.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
}
