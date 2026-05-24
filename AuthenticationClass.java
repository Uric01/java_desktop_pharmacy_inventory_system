/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.healthfirst.models;

import com.assignment.healthfirst.ui.Adminfrm;
import com.assignment.healthfirst.ui.Billingfrm;
import com.assignment.healthfirst.ui.Loginfrm;
import com.assignment.healthfirst.ui.Posfrm;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author zweli
 */
public class AuthenticationClass extends DBConnection{
  
    public static String name;
    public Connection conn;
    public ResultSet rs;
    private Object password;
    private static String userID;
    public PreparedStatement pst;
    Adminfrm admin = new Adminfrm();
    
    
    
    
    
    //Setters
    public void setUserName(String name_){
        name = name_;
     
    }   
    public static void setUserID(String userID_){
        userID = userID_;
    }
    public void setPassword(Object pwrd){
        this.password = pwrd;
    }
    
    //Getters
    public Object getPwrd(){
        return password;
    }
    public static String getUserName(){
        return name;
    }
    public static String getUserID(){
        return userID;
    }
    
    
    
    public AuthenticationClass(){
        try{
            
            conn = connect();
            String sql = "SELECT username, password, role, full_name, user_id FROM users WHERE username=?";
            pst = conn.prepareStatement(sql);
            }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex,"Message",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    public void authenticate(){
        
        try{
            
            String txtUserID = getUserID();
            JPasswordField txtpassword = (JPasswordField) getPwrd();
            String enteredPassword = new String(txtpassword.getPassword());
            pst.setString(1, txtUserID);
            rs = pst.executeQuery();
            
            
            int count = 0;
            if (rs.next()) {
                count++;
                String dbusername = rs.getString("username");
                String dbpassword = rs.getString("password");
                String dbrole = rs.getString("role");
                setUserName(rs.getString("full_name"));
                setUserID(rs.getString("user_id"));
         
                
                if (userID.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter your user ID!");
                } else if (enteredPassword.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter your password!");
                } else if ((txtUserID.equals(dbusername) && enteredPassword.equals(dbpassword))) {
                    if (dbrole.equals("Admin")) {

                        admin.setVisible(true);
                       
                    } else if (dbrole.equals("Cashier")) {
                        Posfrm posSelect = new Posfrm();
                        posSelect.setVisible(true);
                      
                    }
                }else {

                JOptionPane.showMessageDialog(null, "Incorrect User ID or Password, pleas try again!", "Message", JOptionPane.ERROR_MESSAGE);
                }
            } else {

                JOptionPane.showMessageDialog(null, "Incorrect User ID or Password, pleas try again!", "Message", JOptionPane.ERROR_MESSAGE);
                
            }
            
        }catch(HeadlessException | SQLException ex){
        
            JOptionPane.showMessageDialog(null, ex, "Message", JOptionPane.ERROR_MESSAGE);
        }
    }
}