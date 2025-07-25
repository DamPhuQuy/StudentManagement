package com.mycompany.app.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mycompany.app.models.Account;
import com.mycompany.app.models.Enrollment;
import com.mycompany.app.models.Student;
import com.mycompany.app.models.Subjects;
import com.mycompany.app.models.Teacher;
import com.mycompany.app.utilities.animation.Effect;
import com.mycompany.app.utilities.constants.Constants;
import com.mycompany.app.utilities.constants.Gender;
import com.mycompany.app.utilities.constants.Role;
import com.mycompany.app.utilities.helpers.Pair;
import com.mycompany.app.utilities.helpers.Save;
import com.mycompany.app.utilities.io.InformMessage;
import com.opencsv.CSVReader;

public class DataHandler {
    private DataHandler() {}

    public static HashMap<String, Account> fetchAccountData(String path) {
        System.out.println("Fetching data from the database...");
        Effect.progressBar();

        HashMap<String, Account> data = new HashMap<>(); 
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line; 
                while ((line = csvReader.readNext()) != null) {
                    if (line.length < 2) {
                        InformMessage.error("Invalid line format!");
                        continue;  
                    }
                     
                    Pair<String, String> usernamePasswordPair = new Pair<>(line[0], line[1]);
                    Role role = (line[3].equals("STUDENT")) ? Role.STUDENT : Role.TEACHER;

                    Account account = new Account(usernamePasswordPair, role);
                    data.put(account.getUsername(), account); 
                }
            }
        } catch (FileNotFoundException fnf) {
            InformMessage.error("File not found: " + fnf.getMessage());
        } catch (IOException ioe) {
            InformMessage.error("Error reading file: " + ioe.getMessage());
        } catch (Exception e) {
            InformMessage.error("An unexpected error occurred: " + e.getMessage());
        } finally {
            InformMessage.error("Data fetching completed.");
        }
        
        return data; 
    }

    public static List<Student> fetchStudentData(String path, HashMap<String, Account> accounts) {
        System.out.println("Fetching student data from the database...");
        Effect.progressBar();
        
        List<Student> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line; 
                while ((line = csvReader.readNext()) != null) {
                    String username = line.length > 0 ? line[0].trim() : "";
                    if (line.length < 8) {
                        InformMessage.error("Invalid line format!");
                    } else if (!accounts.containsKey(username)) {
                        InformMessage.error("Account not found for student: " + username);
                    } else {
                        Account account = accounts.get(username);
                        String fullName = line[1].trim(); 

                        LocalDate dob = LocalDate.parse(line[2].trim(), Constants.DATE_FORMAT_INPUT); 

                        Gender gender = (line[3].trim().equals("Male")) ? Gender.MALE : Gender.FEMALE;

                        String phone = line[4].trim(); 

                        String email = line[5].trim(); 
                    
                        String address = line[6].trim(); 

                        String className = line[7].trim(); 

                        Student student = new Student(
                            account, 
                            fullName,
                            dob,
                            gender,
                            phone,
                            email,
                            address,
                            className
                        ); 
                        
                        list.add(student); 
                    }
                }
            }
        } catch (FileNotFoundException fnf) {
            InformMessage.error("File not found: " + fnf.getMessage());
        } catch (IOException ioe) {
            InformMessage.error("Error reading file: " + ioe.getMessage());
        } catch (Exception e) {
            InformMessage.error("An unexpected error occurred: " + e.getMessage());
        } finally {
            InformMessage.error("Student data fetching completed.");
        }

        return list;
    } 

    public static Subjects fetchSubjectData(String path, HashMap<String, Account> accounts) {
        System.out.println("Fetching subject data from the database...");
        Effect.progressBar();

        Subjects subject = new Subjects();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    String username = line.length > 0 ? line[0].trim() : ""; 
                    if (line.length < 4) {
                        InformMessage.error("Invalid line format!"); 
                    } 
                    else if (!accounts.containsKey(username)) {
                        InformMessage.error("Account not found for subject: " + username);  
                    }
                    else {
                        String subjectName = line[1].trim(); 
                        LocalDate enrolledDate = LocalDate.parse(line[2].trim(), Constants.DATE_FORMAT_INPUT);
                        Double point = Double.valueOf(line[3].trim()); 

                        subject.addSubject(
                            subjectName,
                            enrolledDate,
                            point
                        ); 
                    }
                }
            }
        } catch (FileNotFoundException fnf) {
            InformMessage.error("File not found: " + fnf.getMessage());
        } catch (IOException ioe) {
            InformMessage.error("Error reading file: " + ioe.getMessage());
        } catch (Exception e) {
            InformMessage.error("An unexpected error occurred: " + e.getMessage());
        } finally {
            InformMessage.error("Subject data fetching completed.");
        }

        return subject;
    }

    public static void saveAccount(String path, HashMap<String, Account> accounts) {
        System.out.println("Saving account data to the database...");
        Effect.progressBar();

        List<String[]> data = new ArrayList<>();
        for (Account account : accounts.values()) {
            String[] line = {
                account.getUsername(),
                account.getPassword(),
                account.getRole().toString()
            };
            data.add(line);
        }
        Save.saveLine(path, data);
    }

    public static void saveStudentInfo(String path, HashMap<String, Enrollment> students) {
        System.out.println("Saving student data to the database...");
        Effect.progressBar();

        List<String[]> data = new ArrayList<>();
        for (Enrollment enrollment : students.values()) {
            String[] line = {
                enrollment.getStudent().getAccount().getUsername(),
                enrollment.getStudent().getFullname(),
                enrollment.getStudent().getDob(),
                enrollment.getStudent().getGender().toString(),
                enrollment.getStudent().getPhone(),
                enrollment.getStudent().getEmail(),
                enrollment.getStudent().getAddress(),
                enrollment.getStudent().getClassName()
            };
            data.add(line);
        }
        Save.saveLine(path, data);
    }

    public static void saveSubjectInfo(String path, HashMap<String, Enrollment> students) {
        System.out.println("Saving subject data to the database...");
        Effect.progressBar();

        List<String[]> data = new ArrayList<>();
        for (Enrollment enrollment : students.values()) {
            for (String subjectName : enrollment.getSubjects().getSubjectsTable().keySet()) {
                String[] line = {
                    enrollment.getStudent().getAccount().getUsername(),
                    subjectName,
                    enrollment.getSubjects().getSubjectsTable().get(subjectName).getFirst().format(Constants.DATE_FORMAT_OUTPUT),
                    String.valueOf(enrollment.getSubjects().getSubjectsTable().get(subjectName).getSecond())
                };
                data.add(line);
            }
        }
        Save.saveLine(path, data);
    }
    
    public static void saveTeacherInfo(String path, HashMap<String, Teacher> teachers) {
        System.out.println("Saving teacher data to the database...");
        Effect.progressBar();

        List<String[]> data = new ArrayList<>();
        for (Teacher teacher : teachers.values()) {
            String[] line = {
                teacher.getAccount().getUsername(),
                teacher.getFullname(),
                teacher.getDob(),
                teacher.getGender().toString(),
                teacher.getEmail(),
                teacher.getPhone(),
                teacher.getAddress(),
                String.join(",", teacher.getCourses()),
                teacher.getLevel()
            };
            data.add(line);
        }
        Save.saveLine(path, data);
    }
}