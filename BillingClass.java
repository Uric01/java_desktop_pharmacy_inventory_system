/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.healthfirst.models;

/**
 *
 * @author zweli
 */
public class BillingClass {
    
    private String medID;
    private String name;
    private String medType;
    private double price;
    private int qty;
    
    ///Setters
    public void setMedID(String MedID){
        this.medID = MedID;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setMedType(String medType){
        this.medType = medType;
    }
    public void setPrice(Double price){
        this.price = price;
    }
    public void setQty(int qty){
        this.qty = qty;
    }
    
    //Getters
    public BillingClass(String x){
        this.name = x;
    }
    public String getMedID(){
        return this.medID;
    }
    public String getName(){
        return name;   
    }
    public Double getPrice(){
        return this.price;
    }
    public int getQty(){
        return this.qty;
    }
    public String getMedType(){
        return this.medType;
    }
    
    public BillingClass(){
    
    }
}
