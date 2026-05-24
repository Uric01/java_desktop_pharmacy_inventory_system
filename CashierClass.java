/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.healthfirst.models;

import com.assignment.healthfirst.ui.point_of_sale_frm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author zweli
 */
public final class CashierClass extends DBConnection{
    public Connection conn = null;
    public Connection saleConn=null;
    public Connection saleConnCheck=null;
    public Connection saleConnInsert=null;
    public Connection stockAvailabilityConn=null;
    public ResultSet rs = null;
    public ResultSet saleRs = null;
    public ResultSet saleRsCheck = null;
    public ResultSet saleRsInsert = null;
    public ResultSet Rs_sale_soh = null;
    public ResultSet checkStockRs = null;
    public ResultSet stockAvailabilityRs = null;
    private String medID = null;
    public PreparedStatement pst = null;
    public PreparedStatement salePst = null;
    public PreparedStatement salePstCheck = null;
    public PreparedStatement salePstInsert = null;
    public PreparedStatement checkStockPst = null;
    public PreparedStatement Pst_sale_soh = null;
    public PreparedStatement stockAvailabilityPst = null;
    private String medName = null;
    private String medType = null;
    private int quantity = 0;
    private double price = 0.0;
    private String userID = null;
    private String userName="";
    String medicineID;
    String medPrice;
    int quantity_in_stock;
    String Sql_sale_soh;
    String stockAvailabilitySql;
    private int availableSOH = 0;
    
    //setters
    public void setavailableSOH(int stock){
        this.availableSOH = stock;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setMedID(String medID){
        this.medID = medID;
    }
    public void setMedName(String medName){
        this.medName = medName;
    }
    public void setMedType(String medType){
        this.medType = medType;
    }
    public void setQuantity(int qty){
        this.quantity = qty;
    }
    public void setPrice(double price){
        this.price = price;
    }
    //getters
    public int getavailableSOH(){
        return availableSOH;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getMedID(){
        return this.medID;
    }
    public String getMedName(){
        return this.medName;
    }
    public String getMedType(){
        return this.medType;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public double getPrice(){
        return this.price;
    }
    
    public CashierClass(){
        try{
            conn = connect();
            String sql = "SELECT medicine_id, name, medicine_type, quantity_in_stock, price FROM medicines WHERE medicine_id=?";
            pst = conn.prepareStatement(sql);
            
                        
        }catch(SQLException ex){
          JOptionPane.showMessageDialog(null, ex, "Message", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public CashierClass(String userName){
        this.setUserName(userName);
    }
    public void cashier(String medId, int qty ){
        try{
           
            quantity = qty;
            medID = medId;
            medicineID = getMedID();
            pst.setString(1,medicineID);
            rs = pst.executeQuery();
            
            if(rs.next()){ 
                
                medName = rs.getString("name");
                medType = rs.getString("medicine_type");
                price = rs.getDouble("price");
            
            }
            
        }catch(SQLException ex){
        
            JOptionPane.showMessageDialog(null, ex, "Message", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void sale(){
        try{
            conn = connect();
            String saleSql = "SELECT user_id FROM users WHERE username=?";
            salePst = conn.prepareStatement(saleSql);
            salePst.setString(1,AuthenticationClass.getUserName());
            saleRs = salePst.executeQuery();
            
            if(saleRs.next()){
               userID = saleRs.getString("user_id");
            }
            
            try{
                String saleSqlCheck = "SELECT * FROM sales";
                salePstCheck = conn.prepareStatement(saleSqlCheck);
                saleRsCheck = salePstCheck.executeQuery();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex, "Message", JOptionPane.ERROR_MESSAGE); System.out.println("Check Error");
            }
            
                try{
                    
                    //Commit sale
                    String saleSqlInsert = "INSERT INTO sales(total_amount,user_id) VALUES(?,?)";
                    salePstInsert = conn.prepareStatement(saleSqlInsert,Statement.RETURN_GENERATED_KEYS);
                    salePstInsert.setString(1,String.valueOf(point_of_sale_frm.totalAmount));
                    salePstInsert.setString(2,AuthenticationClass.getUserID());
                    salePstInsert.executeUpdate();
                    
                    rs = salePstInsert.getGeneratedKeys();
                    int saleId = 0;
                    
                    if (rs.next()) {
                        saleId = rs.getInt(1);
                    }
                    
                    saleSql = "SELECT price FROM medicines WHERE medicine_id = ?";
                    pst = conn.prepareStatement(saleSql);
                    pst.setString(1, getMedID());
                    rs = pst.executeQuery();                    
                    if(rs.next()){
                        medPrice = rs.getString("price");
                    }
                    
                    
                    //Extracting data to update sale_items
                    Sql_sale_soh = "SELECT quantity_in_stock FROM medicines WHERE medicine_id = ?";
                    Pst_sale_soh = conn.prepareStatement(Sql_sale_soh);
                    Pst_sale_soh.setString(1, getMedID());
                    Rs_sale_soh = Pst_sale_soh.executeQuery();
                    if(Rs_sale_soh.next()){
                        quantity_in_stock = Integer.parseInt(Rs_sale_soh.getString("quantity_in_stock"));
                       
                    }
                    
                    quantity_in_stock = quantity_in_stock - getQuantity();
                    
                    //Update SOH
                    String stockSql = "UPDATE medicines SET quantity_in_stock =? WHERE medicine_id = ?";
                    PreparedStatement stockStmt = conn.prepareStatement(stockSql);
                    stockStmt.setString(1, String.valueOf(quantity_in_stock));
                    stockStmt.setString(2, String.valueOf(getMedID()));
                    stockStmt.executeUpdate();
                    
                    
                    //Add the sale to sale_items
                    saleSqlInsert = "INSERT INTO sale_items(sale_id, medicine_id, quantity_sold, price_at_sale) VALUES(?,?,?,?)";
                    salePstInsert = conn.prepareStatement(saleSqlInsert);
                    salePstInsert.setString(1, String.valueOf(saleId));
                    salePstInsert.setString(2, getMedID());
                    salePstInsert.setString(3, String.valueOf(getQuantity()));
                    salePstInsert.setString(4, medPrice);
                    salePstInsert.executeUpdate();
                    
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex, "Message", JOptionPane.ERROR_MESSAGE);
                    
                }
        }catch(SQLException ex){
          JOptionPane.showMessageDialog(null, ex, "Message", JOptionPane.ERROR_MESSAGE);
          
        }
    }
    
        public void checkStock(String medID, JTable tblcs){

            try{
                String sql = "SELECT medicine_id, name, medicine_type, quantity_in_stock, price FROM medicines WHERE medicine_id=?";
                checkStockPst = conn.prepareStatement(sql);
                checkStockPst.setString(1,medID);
                checkStockRs = checkStockPst.executeQuery();

                if(checkStockRs.next()){
                        setMedID(checkStockRs.getString("medicine_id"));
                        setMedName(checkStockRs.getString("name"));
                        setMedType(checkStockRs.getString("medicine_type"));
                        setQuantity(Integer.parseInt(checkStockRs.getString("quantity_in_stock")));                 
                        setPrice(Double.parseDouble(checkStockRs.getString("price")));
                    }
            } catch (SQLException ex) {
                System.getLogger(CashierClass.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }

              DefaultTableModel model = (DefaultTableModel) tblcs.getModel();
              model.addRow(new Object[]{getMedID(),getMedName(),getMedType(),getPrice(),getQuantity()});
        }
        
        public boolean checkStockAvailability(int qty, String medID){
            
            try{
                int Qty = qty;
                String MedID = medID;

                stockAvailabilityConn = connect();
                stockAvailabilitySql = "SELECT quantity_in_stock FROM medicines WHERE medicine_id=?";
                stockAvailabilityPst = stockAvailabilityConn.prepareStatement(stockAvailabilitySql);
                stockAvailabilityPst.setString(1, MedID);
                stockAvailabilityRs = stockAvailabilityPst.executeQuery();
                
                if(stockAvailabilityRs.next()){
                    
                    int dbQty = Integer.parseInt(stockAvailabilityRs.getString("quantity_in_stock"));
                    setavailableSOH(dbQty);
                    
                    if(dbQty>=Qty){
                        
                        return true;
                    }
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex, "Message", JOptionPane.ERROR_MESSAGE);
            
            }
        
            return false;
        }
}
    