import com.mycompany.app.Controller.*;
import com.mycompany.app.Models.Student;
import com.mycompany.app.Models.Subjects;
import com.mycompany.app.Utilities.Constants;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = ReadENV.getConnectionURL();
        String user = ReadENV.getUsername();
        String password = ReadENV.getPassword();

        DB_Connect db = new DB_Connect(url, user, password);

        Scanner input = new Scanner(System.in);

        System.out.println("Enter your choice: ");
        int choice = Integer.parseInt(input.nextLine());
        if (choice == 1) {
            System.out.println("Enter Student ID: ");
            int student_id = Integer.parseInt(input.nextLine());

            System.out.println("Enter Student FirstName: ");
            String firstname = input.nextLine();

            System.out.println("Enter Student Lastname: ");
            String lastname = input.nextLine();

            System.out.println("Enter Student DOB: ");
            String day_of_birth = input.nextLine();

            System.out.println("Enter Student Phone: ");
            String phone = input.nextLine();

            System.out.println("Enter Student Address: ");
            String address = input.nextLine();

            System.out.println("Enter Student ClassName: ");
            String className = input.nextLine();

            Student newStudent = new Student(
                    student_id,
                    firstname,
                    lastname,
                    day_of_birth,
                    phone,
                    address,
                    className);

            db.insertStudentInfo(Constants.studentTable, newStudent);

            System.out.println("Do want to add grades? (Y/N) ");
            String c = input.nextLine();
            if (!c.equals("Y")) {
                System.out.println("Exit");
            } else {
                System.out.println("Enter Math point: ");
                float math = Float.parseFloat(input.nextLine());
                System.out.println("Enter Physics point: ");
                float physics = Float.parseFloat(input.nextLine());
                System.out.println("Enter IT point: ");
                float it = Float.parseFloat(input.nextLine());
                System.out.println("Enter Literature point: ");
                float literature = Float.parseFloat(input.nextLine());
                System.out.println("Enter English point: ");
                float english = Float.parseFloat(input.nextLine());
                System.out.println("Enter Japanese point: ");
                float japan = Float.parseFloat(input.nextLine());

                Subjects subjects = new Subjects(math, physics, it, literature, english, japan);
                newStudent.setSubject(subjects);
                db.insertGradeInfo(Constants.gradesTable, newStudent);
            }
        }
        else if (choice == 2) {
            int yourID = Integer.parseInt(input.nextLine());

            ResultSet rs = db.fetchStudentInfo(Constants.studentTable, yourID);

            try {
                if (rs.next()) { // .next() to start to go to the first record
                    System.out.println("Firstname: " + rs.getString("firstname"));
                    System.out.println("Lastname: " + rs.getString("lastname"));
                    System.out.println("DOB: " + rs.getString("day_of_birth"));
                    System.out.println("Phone: " + rs.getString("phone"));
                    System.out.println("Address: " + rs.getString("address"));
                    System.out.println("ClassName: " + rs.getString("class"));
                } else {
                    System.out.println("No data found.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
