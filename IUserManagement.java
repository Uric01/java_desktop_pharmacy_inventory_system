/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.assignment.healthfirst.models;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author zweli
 */
public interface IUserManagement {
    public void addUser(String username, String password, String role, String full_name);
    public void searchUser(
            String user_id,
            JTextField username, 
            JTextField password, 
            JComboBox role, 
            JTextField full_name,
            JTable jt
    );
    public void updateUser(JTextField user_id, JTextField username, JPasswordField password, JComboBox role, JTextField full_name);
    public void removeUser(String user_id);
}
