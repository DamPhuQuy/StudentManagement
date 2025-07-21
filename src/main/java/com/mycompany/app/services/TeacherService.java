package com.mycompany.app.services;

import java.util.HashMap;

import com.mycompany.app.models.Student;
import com.mycompany.app.models.Teacher;
import com.mycompany.app.utilities.Effect;
import com.mycompany.app.utilities.InformMessage;
import com.mycompany.app.utilities.ProfileViewer;

public class TeacherService {
    private HashMap<String, Teacher> teacherMap;

    public TeacherService() {}

    public TeacherService(HashMap<String, Teacher> teacherMap) {
        this.teacherMap = teacherMap; 
    }

    public HashMap<String, Teacher> getTeacherMap() {
        return teacherMap;
    }

    public void setTeacherMap(HashMap<String, Teacher> teacherMap) {
        this.teacherMap = teacherMap;
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
            System.out.println("Class: " + student.getClassroom());
        } else {
            InformMessage.error("Student not found");
        }
    }
}