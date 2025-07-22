package com.mycompany.app.main;

import java.util.HashMap;
import java.util.Scanner;

import com.mycompany.app.models.Account;
import com.mycompany.app.models.Enrollment;
import com.mycompany.app.models.Teacher;
import com.mycompany.app.services.StudentService;
import com.mycompany.app.services.TeacherService;
import com.mycompany.app.utilities.io.InformMessage;
import com.mycompany.app.services.LoginService;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<String, Enrollment> studentsMap = new HashMap<>();
        HashMap<String, Teacher> teacherMap = new HashMap<>();
        StudentService studentService = new StudentService(studentsMap);
        TeacherService teacherService = new TeacherService(teacherMap, studentService);

        do { 
            LoginService.loginMenu();
            int choice = scanner.nextInt(); 
            switch (choice) {
                case 1 -> {
                    Account account = new Account(); 

                    System.out.println("Enter username: ");
                    account.setUsername(scanner.nextLine()); 

                    System.out.println("Enter password: ");
                    account.setPassword(scanner.nextLine());

                    
                }
            }
            
        } 

        scanner.close();
    }
}