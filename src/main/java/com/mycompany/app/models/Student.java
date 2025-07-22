package com.mycompany.app.models;

import java.time.LocalDate;

import com.mycompany.app.utilities.constants.Constants;
import com.mycompany.app.utilities.constants.Gender;
import com.mycompany.app.utilities.helpers.PersonProfile;

public class Student implements PersonProfile{

    private Account account;
    private String fullname;
    private LocalDate dob;
    private Gender gender;
    private String email;
    private String address;
    private String phone;
    private String className;

    public Student() {}

    public Student(
        Account account,
        String fullname,
        LocalDate dob,
        Gender gender,
        String email,
        String address,
        String phone,
        String className
    ) {
        this.account = account;
        this.fullname = fullname;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.className = className;
    }

    @Override
    public Account getAccount() {
        return account;
    }


    @Override
    public String getFullname() {
        return fullname;
    }

    @Override
    public String getDob() {
        return dob.format(Constants.DATE_FORMAT_OUTPUT);
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    public String getClassName() {
        return className;
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

    public void setClassName(String className) {
        this.className = className;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
