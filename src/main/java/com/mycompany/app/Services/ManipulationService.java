package com.mycompany.app.Services;

import com.mycompany.app.Controller.DB_Connect;
import com.mycompany.app.Utilities.Constants;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManipulationService {
    public static int startLoginService() {
        Scanner scanner = new Scanner(System.in);

        int student_id = -1;
        do {
            MenuService.displayLoginMenu();

            System.out.print("Your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 0 -> {
                    return student_id;
                }
                case 1 -> {
                    student_id = MenuService.LoginImplement();
                }
                case 2 -> {
                    boolean check = MenuService.RegisterImplement();
                    if (check) {
                        student_id = MenuService.LoginImplement();
                    }
                }
                default -> {
                    System.out.println("Invalid choice.");
                }
            }

            if (student_id != -1) {
                System.out.println("Logged in successfully.");
                return student_id;
            } else {
                System.out.println("You have failed to log in. Please try again.");
            }
        } while (true);
    }

    public static int startMainMenu(int student_id) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        DB_Connect db = new DB_Connect();

        do {
            MenuService.displayMainMenu();

            System.out.print("Your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 0 -> {
                    return -1;
                }

                case 1 -> {
                    ResultSet rs = db.fetchInfo(Constants.studentTable, student_id);
                    if (rs.next()) {
                        List<String> studentDetails = new ArrayList<>();

                        for (int i = 2; i <= rs.getMetaData().getColumnCount(); i++) {
                            studentDetails.add(rs.getString(i));
                        }

                        System.out.println("+------------------------------------------+");
                        System.out.printf("|%15sInformation%16s|\n", "", "");
                        System.out.println("+------------------------------------------+");
                        System.out.printf("| %-15s: %-23s |\n", "First name", studentDetails.get(0));
                        System.out.printf("| %-15s: %-23s |\n", "Last name", studentDetails.get(1));
                        System.out.printf("| %-15s: %-23s |\n", "Day of birth", studentDetails.get(2));
                        System.out.printf("| %-15s: %-23s |\n", "Phone", studentDetails.get(3));
                        System.out.printf("| %-15s: %-23s |\n", "Address", studentDetails.get(4));
                        System.out.printf("| %-15s: %-23s |\n", "Class", studentDetails.get(5));
                        System.out.println("+------------------------------------------+");

                    }
                    else {
                        System.out.println("Invalid student ID.");
                    }
                }
                case 2 -> {
                    String chooseTable = "";
                    System.out.println("Enter your data that you would like to update: ");
                    System.out.println("Student Info");
                    System.out.println("Grades Info");
                    chooseTable = scanner.nextLine();

                    chooseTable = (chooseTable.equals("Student Info")) ? Constants.studentTable : Constants.gradesTable;

                    if (db.updateInfo(chooseTable, student_id)) {
                        System.out.println("Student Info updated successfully.");
                    }
                    else {
                        System.out.println("Invalid student ID.");
                    }
                }
                case 3 -> {
                    System.out.print("Are you sure you want to delete your info? (Y/N) ");
                    String c = scanner.nextLine();
                    if (c.equalsIgnoreCase("Y")) {
                        System.out.println("Every information will be deleted, even the account.");

                        db.deleteInfo(Constants.studentTable, student_id);
                        db.deleteInfo(Constants.gradesTable, student_id);
                        db.deleteInfo(Constants.loginTable, student_id);
                    }
                }
                default -> {
                    System.out.println("Invalid choice.");
                }
            }
        } while (true);
    }
}
