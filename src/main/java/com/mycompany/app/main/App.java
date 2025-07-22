package com.mycompany.app.main;

import java.util.HashMap;
import java.util.Scanner;

import com.mycompany.app.models.Account;
import com.mycompany.app.models.Enrollment;
import com.mycompany.app.models.Teacher;
import com.mycompany.app.services.StudentService;
import com.mycompany.app.services.TeacherService;
import com.mycompany.app.utilities.helpers.Pair;
import com.mycompany.app.utilities.constants.Role;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Enrollment> studentsMap = new HashMap<>();
        HashMap<String, Teacher> teacherMap = new HashMap<>();
        StudentService studentService = new StudentService(studentsMap);
        TeacherService teacherService = new TeacherService(teacherMap, studentService);

        boolean running = true;
        while (running) {
            com.mycompany.app.utilities.io.InformMessage.info("==== Student Management System ====");
            System.out.println("1. Quản lý Sinh viên");
            System.out.println("2. Quản lý Giáo viên");
            System.out.println("3. Thoát");
            System.out.print("Chọn chức năng: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    studentMenu(scanner, studentService);
                    break;
                case "2":
                    teacherMenu(scanner, teacherService);
                    break;
                case "3":
                    running = false;
                    com.mycompany.app.utilities.io.InformMessage.info("Đang thoát chương trình...");
                    break;
                default:
                    com.mycompany.app.utilities.io.InformMessage.warning("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void studentMenu(Scanner scanner, StudentService studentService) {
        boolean back = false;
        while (!back) {
            studentService.showMenu(Role.STUDENT);
            System.out.print("Chọn chức năng: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    // Xem profile sinh viên
                    System.out.print("Nhập username sinh viên: ");
                    String username = scanner.nextLine();
                    Enrollment enrollment = studentService.getStudentsMap().get(username);
                    if (enrollment != null) {
                        studentService.viewProfile(enrollment.getStudent());
                    } else {
                        com.mycompany.app.utilities.io.InformMessage.error("Không tìm thấy sinh viên.");
                    }
                    break;
                case "3":
                    // Xem enrollment sinh viên
                    System.out.print("Nhập username sinh viên: ");
                    username = scanner.nextLine();
                    enrollment = studentService.getStudentsMap().get(username);
                    if (enrollment != null) {
                        studentService.viewEnrollment(enrollment.getStudent());
                    } else {
                        com.mycompany.app.utilities.io.InformMessage.error("Không tìm thấy sinh viên.");
                    }
                    break;
                case "4":
                    // Cập nhật thông tin sinh viên
                    System.out.print("Nhập username sinh viên cần cập nhật: ");
                    username = scanner.nextLine();
                    enrollment = studentService.getStudentsMap().get(username);
                    if (enrollment != null) {
                        System.out.print("Nhập tên mới: ");
                        String newName = scanner.nextLine();
                        enrollment.getStudent().setFullname(newName);
                        studentService.updateStudent(enrollment.getStudent());
                        com.mycompany.app.utilities.io.InformMessage.success("Cập nhật thành công.");
                    } else {
                        com.mycompany.app.utilities.io.InformMessage.error("Không tìm thấy sinh viên.");
                    }
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    com.mycompany.app.utilities.io.InformMessage.warning("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private static void teacherMenu(Scanner scanner, TeacherService teacherService) {
        boolean back = false;
        while (!back) {
            teacherService.showMenu(Role.TEACHER);
            System.out.print("Chọn chức năng: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    // Xem profile giáo viên
                    System.out.print("Nhập username giáo viên: ");
                    String username = scanner.nextLine();
                    Teacher teacher = teacherService.getTeacherMap().get(username);
                    if (teacher != null) {
                        teacherService.viewProfile(teacher);
                    } else {
                        com.mycompany.app.utilities.io.InformMessage.error("Không tìm thấy giáo viên.");
                    }
                    break;
                case "2":
                    // Xem profile sinh viên
                    System.out.print("Nhập username sinh viên: ");
                    username = scanner.nextLine();
                    Enrollment enrollment = teacherService.getStudentService().getStudentsMap().get(username);
                    if (enrollment != null) {
                        teacherService.viewStudentProfile(enrollment.getStudent());
                    } else {
                        com.mycompany.app.utilities.io.InformMessage.error("Không tìm thấy sinh viên.");
                    }
                    break;
                case "3":
                    // Xem enrollment sinh viên
                    System.out.print("Nhập username sinh viên: ");
                    username = scanner.nextLine();
                    enrollment = teacherService.getStudentService().getStudentsMap().get(username);
                    if (enrollment != null) {
                        teacherService.viewStudentEnrollment(enrollment.getStudent());
                    } else {
                        com.mycompany.app.utilities.io.InformMessage.error("Không tìm thấy sinh viên.");
                    }
                    break;
                case "4":
                    // Cập nhật thông tin giáo viên
                    System.out.print("Nhập username giáo viên cần cập nhật: ");
                    username = scanner.nextLine();
                    teacher = teacherService.getTeacherMap().get(username);
                    if (teacher != null) {
                        System.out.print("Nhập tên mới: ");
                        String newName = scanner.nextLine();
                        teacher.setFullname(newName);
                        teacherService.setTeacherMap(teacherService.getTeacherMap());
                        com.mycompany.app.utilities.io.InformMessage.success("Cập nhật thành công.");
                    } else {
                        com.mycompany.app.utilities.io.InformMessage.error("Không tìm thấy giáo viên.");
                    }
                    break;
                case "5":
                    // Cập nhật enrollment sinh viên
                    System.out.print("Nhập username sinh viên cần cập nhật: ");
                    username = scanner.nextLine();
                    enrollment = teacherService.getStudentService().getStudentsMap().get(username);
                    if (enrollment != null) {
                        System.out.print("Nhập điểm mới cho tất cả môn học: ");
                        try {
                            double newGrade = Double.parseDouble(scanner.nextLine());
                            for (String subject : enrollment.getSubjects().getSubjectsTable().keySet()) {
                                enrollment.getSubjects().getSubjectsTable().get(subject).setSecond(newGrade);
                            }
                            com.mycompany.app.utilities.io.InformMessage.success("Cập nhật điểm thành công.");
                        } catch (NumberFormatException e) {
                            com.mycompany.app.utilities.io.InformMessage.error("Điểm không hợp lệ.");
                        }
                    } else {
                        com.mycompany.app.utilities.io.InformMessage.error("Không tìm thấy sinh viên.");
                    }
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    com.mycompany.app.utilities.io.InformMessage.warning("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
}