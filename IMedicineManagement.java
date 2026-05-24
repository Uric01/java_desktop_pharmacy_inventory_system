/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.assignment.healthfirst.models;

import javax.swing.JTable;

/**
 *
 * @author zweli
 */
public interface IMedicineManagement {
    
    public void addMedicine(String name, 
            String medicineType, 
            double price, 
            int quantityInStock, 
            int reOrderLevel, 
            String expiryDate,
            String company,
            String supplierID);
    public void searchMedicine(String medID, JTable jt);
    public void updateMedicine(String medID,
            String name, 
            String medicineType, 
            double price, 
            int quantityInStock, 
            int reOrderLevel, 
            String expiryDate,
            String company,
            String supplierID);
    public void removeMedicine(String medID);
    
}
