package com.mycompany.app.services;

import java.util.HashMap;
import java.util.Map;

import com.mycompany.app.models.Enrollment;
import com.mycompany.app.models.Student;
import com.mycompany.app.models.Teacher;
import com.mycompany.app.utilities.animation.Effect;
import com.mycompany.app.utilities.constants.Role;
import com.mycompany.app.utilities.helpers.Menu;
import com.mycompany.app.utilities.io.InformMessage;
import com.mycompany.app.utilities.io.ProfileViewer;

public class TeacherService implements Menu {
    private HashMap<String, Teacher> teacherMap;
    private StudentService studentService;

    public TeacherService() {}

    public TeacherService(
        HashMap<String, Teacher> teacherMap, 
        StudentService studentService
    ) {
        this.teacherMap = teacherMap; 
        this.studentService = studentService; 
    }

    public HashMap<String, Teacher> getTeacherMap() {
        return teacherMap;
    }

    public void setTeacherMap(HashMap<String, Teacher> teacherMap) {
        this.teacherMap = teacherMap;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void addTeacher(Teacher newTeacher) {
        String username = newTeacher.getAccount().getUsername(); 

        if (teacherMap.containsKey(username)) {
            InformMessage.error("Teacher with this username already exists.");
            return; 
        }
        teacherMap.put(username, newTeacher); 
    }

    public void updateUsername(String oldUsername, String newUsername, Teacher teacher) {
        Teacher oldInfo = teacherMap.get(oldUsername); 
        if (oldInfo == null) {
            InformMessage.error("Teacher not found!");
            return; 
        }

        teacher.getAccount().setUsername(newUsername);
        teacherMap.remove(oldUsername); 
        teacherMap.put(newUsername, teacher); 
        InformMessage.success("Username updated successfully!");
    }

    public void viewProfile(Teacher teacher) {
        Effect.progressBar();
        if (teacherMap.containsKey(teacher.getAccount().getUsername())) {
            System.out.println("Student Profile:");

            ProfileViewer.viewBasicProfile(teacher);

            System.out.print("Subjects: ");
            for (String subject : teacher.getCourses()) {
                System.out.print(subject + ", ");
            }
            System.out.println(); 

            System.out.println("Level: " + teacher.getLevel()); 
        } else {
            InformMessage.error("Teacher not found");
        }
    }

    public void viewStudentProfile(Student student) {
        Effect.progressBar();

        StudentService tempService = new StudentService(); 
        if (tempService.getStudentsMap().containsKey(student.getAccount().getUsername())) {
            System.out.println("Student Profile:");
            ProfileViewer.viewBasicProfile(student);
            System.out.println("Class: " + student.getClassName());
        } else {
            InformMessage.error("Student not found");
        }
    }

    public void viewStudentEnrollment(Student student) {
        Effect.progressBar();

        Map<String, Enrollment> map = studentService.getStudentsMap();
        String username = student.getAccount().getUsername();

        if (map.containsKey(username)) {
            Enrollment enrollment = map.get(username);
            if (enrollment != null) {
                ProfileViewer.printEnrollment(student, enrollment);
            } else {
                InformMessage.error("No enrollment found");
            }
        } else {
            InformMessage.error("Student not found");
        }
    }

    @Override
    public void showMenu(Role role) {
        System.out.println("-----MENU-----"); 
        System.out.println("Welcome " + role.toString()); 
        System.out.println("1. View Profile"); 
        System.out.println("2. View Student Profile");
        System.out.println("3. View Student Enrollment"); 
        System.out.println("4. Update Information"); 
        System.out.println("5. Update Student Enrollment"); 
        System.out.println("0. Back to menu"); 
    }   
}