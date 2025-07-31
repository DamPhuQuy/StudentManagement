import com.mycompany.app.Controller.*;
import com.mycompany.app.Models.Student;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = ReadENV.getConnectionURL();
        String user = ReadENV.getUsername();
        String password = ReadENV.getPassword();

        DB_Connect db = new DB_Connect(url, user, password);

        Scanner input = new Scanner(System.in);

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

        Student student = new Student(
                student_id,
                firstname,
                lastname,
                day_of_birth,
                phone,
                address,
                className);

        db.insertDB("student_info",  student);
    }
}
