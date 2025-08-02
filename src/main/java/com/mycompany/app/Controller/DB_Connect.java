package com.mycompany.app.Controller;

import com.mycompany.app.Models.Student;
import com.mycompany.app.Models.Subjects;
import com.mycompany.app.Utilities.Constants;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class DB_Connect {
    private final String url = ReadENV.getConnectionURL();
    private final String user = ReadENV.getUsername();
    private final String password =  ReadENV.getPassword();

    public DB_Connect() {}

    public boolean register(String table, int student_id, String passwordAcc) {
        String insertQuery = "INSERT INTO " + table + " VALUES (?, ?)";

        try {
            Class.forName(Constants.driver);

            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement pstmt = con.prepareStatement(insertQuery);
            pstmt.setInt(1, student_id);
            pstmt.setString(2, passwordAcc);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Successfully inserted!");
                return true;
            }
            else {
                return false;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

        return false;
    }

    public boolean login(String table, int student_id, String passwordAcc) {
        String selectQuery = "SELECT student_id, password FROM " + table + " WHERE student_id = ? AND password = ?";

        try {
            Class.forName(Constants.driver);

            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement pstmt = con.prepareStatement(selectQuery);
            pstmt.setInt(1, student_id);
            pstmt.setString(2, passwordAcc);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            else {
                return false;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

        return false;
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

    public ResultSet fetchInfo(String table, int id) {
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

    public boolean updateInfo(String table, int student_id) {
        Scanner scanner = new Scanner(System.in);

        List<String> validAttributes;

        if (table.equals(Constants.studentTable)) {
            validAttributes = List.of("firstname", "lastname", "day_of_birth", "phone", "address", "class");
        } else {
            validAttributes = List.of("math", "physics", "it", "literature", "english", "japanese");
        }

        System.out.print("How many attributes would you like to update?: ");
        int cols = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter your attributes which you would like to update:");
        for (String attr : validAttributes) {
            System.out.println(attr);
        }

        List<String> attributes = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            String attr = scanner.nextLine();
            if (!validAttributes.contains(attr)) {
                System.out.println("Invalid attribute: " + attr);
                return false;
            }
            attributes.add(attr);
        }

        Map<String, String> updateValues = new LinkedHashMap<>();
        for (String attr : attributes) {
            System.out.print("Enter value for " + attr + ": ");
            String value = scanner.nextLine();
            updateValues.put(attr, value);
        }

        String setClause = attributes.stream()
                .map(attr -> attr + " = ?")
                .collect(Collectors.joining(", "));
        String updateQuery = "UPDATE " + table + " SET " + setClause + " WHERE student_id = ?";

        try {
            Class.forName(Constants.driver);
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = con.prepareStatement(updateQuery.toString());

            int index = 1;
            for (String value : updateValues.values()) {
                pstmt.setString(index++, value);
            }
            pstmt.setInt(index, student_id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Successfully updated " + student_id);
                return true;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteInfo(String table, int student_id) {
        String deleteQuery = "DELETE FROM " + table + " WHERE student_id = ?";

        try {
            Class.forName(Constants.driver);

            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement pstmt = con.prepareStatement(deleteQuery);

            pstmt.setInt(1, student_id);

            int rowUpdate = pstmt.executeUpdate();
            if (rowUpdate > 0) {
                System.out.println("Successfully deleted " + student_id);
                return true;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return false;
    }
}
