package com.mycompany.app.Services;

import com.mycompany.app.Controller.DB_Connect;
import com.mycompany.app.Models.Student;
import com.mycompany.app.Models.Subjects;
import com.mycompany.app.Utilities.Constants;

import java.sql.Connection;
import java.util.Scanner;

public class MenuService {
    public static void displayMainMenu() {
        System.out.println("----------------------------------");
        System.out.println("|              MENU              |");
        System.out.println("+--------------------------------+");
        System.out.println("| 1. View student information    |");
        System.out.println("| 2. Add student information     |");
        System.out.println("| 3. Update student information  |");
        System.out.println("| 4. Delete student information  |");
        System.out.println("| 0. Quit                        |");
        System.out.println("----------------------------------");
    }

    public static void displayLoginMenu() {
        System.out.println("----------------------------");
        System.out.println("|           LOGIN          |");
        System.out.println("+--------------------------+");
        System.out.println("| 1. Login                 |");
        System.out.println("| 2. Register              |");
        System.out.println("| 0. Exit                  |");
        System.out.println("----------------------------");
    }

    public static int LoginImplement() {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.print("Enter your ID: ");
                int student_id = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter your Password: ");
                String pass = scanner.nextLine();

                DB_Connect db = new DB_Connect();
                if (db.login(Constants.loginTable, student_id, pass)) {
                    return student_id;
                } else {
                    return -1;
                }
            } catch (NullPointerException npe) {
                System.out.println("Wrong id input");
            }
        } while (true);
    }

    public static boolean RegisterImplement() {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.print("Enter your new ID: ");
                int student_id = Integer.parseInt(scanner.nextLine());

                System.out.println("Enter your new Password: ");
                String pass = scanner.nextLine();

                DB_Connect db = new DB_Connect();

                db.register(Constants.loginTable, student_id, pass);

                System.out.println("Enter Student FirstName: ");
                String firstname = scanner.nextLine();

                System.out.println("Enter Student Lastname: ");
                String lastname = scanner.nextLine();

                System.out.println("Enter Student DOB: ");
                String day_of_birth = scanner.nextLine();

                System.out.println("Enter Student Phone: ");
                String phone = scanner.nextLine();

                System.out.println("Enter Student Address: ");
                String address = scanner.nextLine();

                System.out.println("Enter Student ClassName: ");
                String className = scanner.nextLine();

                Student newStudent = new Student(
                        student_id,
                        firstname,
                        lastname,
                        day_of_birth,
                        phone,
                        address,
                        className);

                db.insertStudentInfo(Constants.studentTable, newStudent);

                System.out.println("Enter Math point: ");
                float math = Float.parseFloat(scanner.nextLine());

                System.out.println("Enter Physics point: ");
                float physics = Float.parseFloat(scanner.nextLine());

                System.out.println("Enter IT point: ");
                float it = Float.parseFloat(scanner.nextLine());

                System.out.println("Enter Literature point: ");
                float literature = Float.parseFloat(scanner.nextLine());

                System.out.println("Enter English point: ");
                float english = Float.parseFloat(scanner.nextLine());

                System.out.println("Enter Japanese point: ");
                float japan = Float.parseFloat(scanner.nextLine());

                Subjects subjects = new Subjects(math, physics, it, literature, english, japan);
                newStudent.setSubject(subjects);
                db.insertGradeInfo(Constants.gradesTable, newStudent);

                return true;
            } catch (NullPointerException npe) {
                System.out.println("Wrong id input");
                return false;
            }
        } while (true);
    }
}

