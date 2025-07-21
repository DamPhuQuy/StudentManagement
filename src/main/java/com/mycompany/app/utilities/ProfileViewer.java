package com.mycompany.app.utilities;

import com.mycompany.app.models.PersonProfile;

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
}
