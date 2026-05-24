/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.assignment.healthfirst.models;

import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author zweli
 */
public interface ISupplierManagement {
    public void addSupplier(
            String name, 
            String contact_person, 
            String phone, 
            String email, 
            String address
    );
    public void searchSupplier(
            String supplier_id,
            JTable jt,
            JTextField name, 
            JTextField contact_person, 
            JTextField phone, 
            JTextField email, 
            JTextField address
    );
    public void updateSupplier(
            String supplier_id,
            String supplierName, 
            String supplierContact_person, 
            String supplierPhone, 
            String supplierEmail, 
            String supplierAddress
    );
    public void removeSupplier(String supplier_id);
}
