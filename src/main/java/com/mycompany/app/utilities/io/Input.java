package com.mycompany.app.utilities.io;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mycompany.app.models.Account;
import com.mycompany.app.models.Student;
import com.mycompany.app.models.Subjects;
import com.mycompany.app.models.Teacher;
import com.mycompany.app.utilities.constants.Constants;
import com.mycompany.app.utilities.constants.Gender;

public class Input {

    private Input() {}

    public static Student insertStudentInfo(Account account) {
        Scanner scanner = new Scanner(System.in);
        Student newStudent = new Student();

        newStudent.setAccount(account);

        System.out.println("Enter fullname: ");
        newStudent.setFullname(scanner.nextLine());

        System.out.println("Day of birth: ");
        LocalDate dob = LocalDate.parse(scanner.nextLine());
        newStudent.setDob(dob);

        System.out.println("Gender: ");
        Gender gender = Gender.valueOf(scanner.nextLine());
        newStudent.setGender(gender);

        String email;
        while (true) {
            System.out.println("Email: ");
            email = scanner.nextLine();
            Pattern pattern = Pattern.compile("^[a-zA-z0-9._]+@gmail.com$");
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                InformMessage.error("Invalid email format");
                continue;
            }
            newStudent.setEmail(email);
            break;
        }

        String phone;
        while (true) {
            System.out.println("Phone number: ");
            phone = scanner.nextLine();
            Pattern pattern = Pattern.compile("^0{1}[0-9]{9}$");
            Matcher matcher = pattern.matcher(phone);
            if (!matcher.matches()) {
                InformMessage.error("Invalid phone number format");
                continue;
            }
            newStudent.setPhone(phone);
            break;
        }

        System.out.println("Address: ");
        String address = scanner.nextLine();
        newStudent.setAddress(address);

        String classCode;
        while (true) {
            System.out.println("Class: ");
            classCode = scanner.nextLine();
            Pattern pattern = Pattern.compile("^[0-1]{1}[1-9]{1}/[A-Z]{1}");
            Matcher matcher = pattern.matcher(classCode);
            if (!matcher.matches()) {
                InformMessage.error("Invalid class format");
                continue;
            }
            newStudent.setclassName(classCode);
            break;
        }

        scanner.close();
        return newStudent;
    }

    public static Subjects insertStudentSubjects() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Number of subjects in semester: ");
        int numberOfSubjects = Integer.parseInt(scanner.nextLine());

        Subjects subjects = new Subjects();
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.println("Subject name: ");
            String subjectName = scanner.nextLine();

            LocalDate enrolledDate;
            while (true) {
                System.out.println("Enrolled Date (dd/MM/yyyy): ");
                String input = scanner.nextLine();
                try {
                    enrolledDate = LocalDate.parse(
                        input,
                        Constants.DATE_FORMAT
                    );
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid date format!");
                }
            }

            System.out.println("Point: ");
            double point = Double.parseDouble(scanner.nextLine());

            subjects.addSubject(subjectName, enrolledDate, point);
        }

        scanner.close();
        return subjects;
    }

    public static Teacher InsertTeacherInfo(Account account) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Teacher Fullname: ");
        String fullname = scanner.nextLine();

        System.out.println("Date of Birth (dd/MM/yyyy): ");
        String dobInput = scanner.nextLine();
        LocalDate dob = LocalDate.parse(dobInput, Constants.DATE_FORMAT);

        System.out.println("Gender (M/F): ");
        String genderInput = scanner.nextLine();
        Gender gender = genderInput.equalsIgnoreCase("M")
            ? Gender.MALE
            : Gender.FEMALE;

        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Phone: ");
        String phone = scanner.nextLine();

        System.out.println("Address: ");
        String address = scanner.nextLine();

        System.out.println("Courses: ");
        String coursesInput = scanner.nextLine();
        List<String> courses = Arrays.asList(coursesInput.split(","));

        System.out.println("Level: ");
        String level = scanner.nextLine();

        Teacher teacher = new Teacher(
            fullname,
            dob,
            gender,
            email,
            phone,
            address,
            courses,
            level,
            account
        );

        scanner.close();
        return teacher;
    }
}
