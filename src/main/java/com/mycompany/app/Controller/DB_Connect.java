package com.mycompany.app.Controller;

import com.mycompany.app.Models.Student;
import com.mycompany.app.Models.Subjects;
import com.mycompany.app.Utilities.Constants;

import java.sql.*;

public class DB_Connect {
    private String url;
    private String user;
    private String password;

    public DB_Connect() {}

    public DB_Connect(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void insertStudentInfo(String table, Student newStudent) {
        String insertQuery = "INSERT INTO "+ table + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Establish JDBC connection
        try {
            // Load the JDBC Driver
            Class.forName(Constants.driver);

            // Establish connection
            Connection con = DriverManager.getConnection(url, user, password);

            // Create a PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(insertQuery);

            // Execute a query
            pstmt.setInt(1, newStudent.getStudent_id());
            pstmt.setString(2, newStudent.getFirstname());
            pstmt.setString(3, newStudent.getLastname());
            pstmt.setDate(4, java.sql.Date.valueOf(newStudent.getDay_of_birth()));
            pstmt.setString(5, newStudent.getPhone());
            pstmt.setString(6, newStudent.getAddress());
            pstmt.setString(7, newStudent.getClassName());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Successfully inserted " + newStudent.getFirstname() + " " + newStudent.getLastname());
            }

            // close
            pstmt.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

    public ResultSet fetchStudentInfo(String table, int id) {
        String selectQuery = "SELECT * FROM " + table + " WHERE student_id = ?";

        try {
            Class.forName(Constants.driver);

            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement pstmt = con.prepareStatement(selectQuery);
            pstmt.setInt(1, id);

            return pstmt.executeQuery();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

        return null;
    }

    public void insertGradeInfo(String table, Student newStudent) {
        String insertQuery = "INSERT INTO "+ table + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Class.forName(Constants.driver);

            Connection con = DriverManager.getConnection(url, user, password);

            Subjects subject = newStudent.getSubject();

            PreparedStatement pstmt = con.prepareStatement(insertQuery);
            pstmt.setInt(1, newStudent.getStudent_id());
            pstmt.setFloat(2, subject.getMath());
            pstmt.setFloat(3, subject.getPhysics());
            pstmt.setFloat(4, subject.getIt());
            pstmt.setFloat(5, subject.getLiterature());
            pstmt.setFloat(6, subject.getEnglish());
            pstmt.setFloat(7, subject.getJapanese());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Successfully inserted " + newStudent.getFirstname() + " " + newStudent.getLastname() + " points");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
