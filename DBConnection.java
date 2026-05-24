/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.healthfirst.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author zweli
 */
class DBConnection {
    public static Connection connect() {

        Connection conn = null;

        try {

            String url = "jdbc:mysql://localhost:3306/pharmacy_db";
            String user = "root";
            String password = "";   // XAMPP default password is empty
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

        } catch (SQLException e) {
            System.out.println("Connection failed");
            JOptionPane.showMessageDialog(null, e,"Message", JOptionPane.ERROR_MESSAGE);
        }

        return conn;
    }
}
