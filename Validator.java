/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.healthfirst.models;

/**
 *
 * @author zweli
 */
public class Validator {
    
    public static boolean isValidDouble(String textFied){
        
        try {
            Double.parseDouble(textFied);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isValidInteger(String textFied) {

        try {
            Integer.parseInt(textFied);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
        
    
    
}
