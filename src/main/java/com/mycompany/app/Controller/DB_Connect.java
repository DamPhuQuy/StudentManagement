package com.mycompany.app.Controller;

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

    public void insertDB() {
        String insertQuery = "INSERT INTO student_info (" +
                "student_id, firstname, lastname, day_of_birth, phone, address, class" +
                ") VALUES ('1', 'John', 'Doe', '2000-01-01', '0123456789', '123 Main St', 'CS101')";


        // Establish JDBC connection
        try {
            // Load the JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection(url, user, password);

            // Create a Statement
            Statement stmt = con.createStatement();

            // Execute a query
            int rows =  stmt.executeUpdate(insertQuery);
            System.out.println("Rows affected: " + rows);

            // close
            stmt.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
