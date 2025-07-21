package com.mycompany.app.models;

import java.time.LocalDate;
import java.util.List;

import com.mycompany.app.utilities.constants.Constants;
import com.mycompany.app.utilities.constants.Gender;

public class Teacher implements PersonProfile {

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

    @Override
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String getDob() {
        return dob.format(Constants.DATE_FORMAT);
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
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

    @Override
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
