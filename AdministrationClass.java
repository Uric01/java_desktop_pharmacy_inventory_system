/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.healthfirst.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
/**
 *
 * @author zweli
 */
public class AdministrationClass extends DBConnection implements IMedicineManagement, IReportGeneration, ISupplierManagement,IUserManagement {
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private String sql = null;
    private String medicine_id, name, company, medicine_type, supplier_id, expiry_date = null;
    private int quantity_in_stock, reorder_level =0;
    private double price = 0.0;
    
    //for static functions
    public static Connection con;
    public static String SQL;
    public static PreparedStatement PST;
    public static ResultSet RS;
    private static String Medicine_id, Name, Company, Medicine_type, Supplier_id, Expiry_date, day, month, year = null;
    private static int Quantity_in_stock, Reorder_level =0;
    private static double Price = 0.0;
   
//Medicine Management
    @Override
    public void addMedicine(String name, 
            String medicineType, 
            double price, 
            int quantityInStock, 
            int reOrderLevel, 
            String expiryDate,
            String company,
            String supplierID) {
       try{
           conn = connect();
           sql = "INSERT INTO medicines(name, medicine_type, price, quantity_in_stock, reorder_level, expiry_date, supplier_id, company) VALUES(?,?,?,?,?,?,?,?)";
           pst = conn.prepareStatement(sql);
           pst.setString(1, name);
           pst.setString(2,medicineType);
           pst.setDouble(3,price);
           pst.setInt(4,quantityInStock);
           pst.setInt(5, reOrderLevel);
           pst.setString(6,expiryDate);
           pst.setString(7,supplierID);
           pst.setString(8,company);
           pst.executeUpdate();
       
       }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, ex,"Message", JOptionPane.ERROR_MESSAGE);
       }
    }

    @Override
    public void searchMedicine(String medID, JTable jt) {
        
        try{
            conn = connect();
            sql = "SELECT * FROM medicines WHERE medicine_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,medID);
            rs = pst.executeQuery();
            
            if(rs.next()){
                DefaultTableModel model = (DefaultTableModel) jt.getModel();
                model.addRow(new Object[]{medicine_id, 
                    name, 
                    company, 
                    medicine_type, 
                    price, 
                    quantity_in_stock, 
                    reorder_level, 
                    expiry_date, 
                    supplier_id});
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex,"Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void updateMedicine(String medID,
            String name, 
            String medicineType, 
            double price, 
            int quantityInStock, 
            int reOrderLevel, 
            String expiryDate,
            String company,
            String supplierID){
        
        try{
            conn = connect();
            sql = "UPDATE medicines SET name =?,"
                    + "company=?, "
                    + "medicine_type=?,"
                    + "price=?,"
                    + "quantity_in_stock=?,"
                    + "reorder_level=?,"
                    + "expiry_date=?,"
                    + "supplier_id=? "
                    + "WHERE medicine_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, company);
            pst.setString(3, medicineType);
            pst.setDouble(4, price);
            pst.setInt(5, quantityInStock);
            pst.setInt(6, reOrderLevel);
            pst.setString(7, expiryDate);
            pst.setString(8, supplierID);
            pst.setString(9, medID);
            pst.executeUpdate();

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex,"Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void removeMedicine(String medID) {
        try{
            conn = connect();
            sql ="DELETE FROM medicines WHERE medicine_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, medID);
            pst.executeUpdate();
        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex,"Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    //User Management
    @Override
    public void userCashierReports() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //Report Generation
    @Override
    public void salesReports(JPanel jpRevenue, JPanel jpQty) {
        try{
            double yTotalRandSales;
            String xSoldItems;
            String legend = "Sales";
            String chartTitle = "Medicine Sales Report";
            String xTitle = "Medicine";
            String yTitle = "Revenue in Rands";
            
            int yTotalQtySold;
            String legendQty = "Total Quantity Sold";
            String chartTitleQty = "Total Quantity of Medicine Sold";
            String xTitleQty = "Medicine";
            String yTitleQty = "Quantity";
            
            
            conn = connect();
            sql = """
                  SELECT 
                      m.medicine_id,
                      m.name,
                      SUM(si.quantity_sold) AS total_quantity,
                      SUM(si.quantity_sold * si.price_at_sale) AS total_revenue
                  FROM sale_items si
                  JOIN medicines m ON si.medicine_id = m.medicine_id
                  GROUP BY m.medicine_id, m.name
                  ORDER BY total_quantity DESC;""";
            
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            DefaultCategoryDataset datasetRevenue = new DefaultCategoryDataset();
            DefaultCategoryDataset datasetQtySold = new DefaultCategoryDataset();
            while(rs.next()){
                yTotalRandSales = rs.getDouble("total_revenue");
                yTotalQtySold = rs.getInt("total_quantity");
                xSoldItems = rs.getString("m.name");
               
                datasetRevenue.setValue(yTotalRandSales, legend, xSoldItems);
                ReportChartsClass.getChartContainer(jpRevenue, chartTitle, xTitle, yTitle,datasetRevenue);
                
                datasetQtySold.setValue(yTotalQtySold, legendQty, xSoldItems);
                ReportChartsClass.getChartContainer(jpQty,chartTitleQty,xTitleQty,yTitleQty, datasetQtySold);
            }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex, "Message", JOptionPane.ERROR_MESSAGE);
                
        }
    }

    @Override
    public void lowStockReorderReports(DefaultTableModel model) {
        try{
            String medID_low = null;
            String name_low = null;
            int quantity_in_stock_low = 0;
            int reorder_level_low =0;
            
            sql = "SELECT medicine_id, name,quantity_in_stock, reorder_level FROM medicines";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
             medID_low = rs.getString("medicine_id");
             name_low = rs.getString("name");
             quantity_in_stock_low = rs.getInt("quantity_in_stock");
             reorder_level_low = rs.getInt("reorder_level");
             
             if((quantity_in_stock_low<=reorder_level_low)){
                 model.addRow(new Object[]{medID_low,name_low,quantity_in_stock_low} );
             }
            }
        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex, "Message", JOptionPane.ERROR_MESSAGE);
        
        }
        
    }

    @Override
    public void expiryBatchReports(DefaultTableModel model) {      
        try{
            sql = "SELECT medicine_id, name, expiry_date FROM medicines ORDER BY expiry_date ASC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            LocalDate today = LocalDate.now();
            LocalDate expiryDate;
            long daysBetween;
            String medID;
            String name_expiry;
        
            while(rs.next()){
                medID =rs.getString("medicine_id");
                name_expiry = rs.getString("name");
                expiryDate = LocalDate.parse(rs.getString("expiry_date"));
                daysBetween = ChronoUnit.DAYS.between(today, expiryDate);
                
                model.addRow(new Object[]{medID,name_expiry,daysBetween});
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex, "Message", JOptionPane.ERROR_MESSAGE);
            
        }
        
    }

    @Override
    public void inventoryMovementReports() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void profitMarginReports() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //Supplier Management
    @Override
    public void addSupplier(String name, String contact_person, String phone, String email, String address) {
       
        try{
        
            conn = connect();
            sql = "INSERT INTO suppliers(name, contact_person, phone, email, address) VALUES(?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, contact_person);
            pst.setString(3, phone);
            pst.setString(4, email);
            pst.setString(5, address);
            pst.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex, "Message", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    
    String supplierName;
    String supplierContact_person;
    String supplierPhone;
    String supplierEmail;
    String supplierAddress;
    
    @Override
    public void searchSupplier(
            String supplier_id,
            JTable jt,
            JTextField name, 
            JTextField contact_person, 
            JTextField phone, 
            JTextField email, 
            JTextField address){
       try{
            conn = connect();
            sql ="SELECT * FROM suppliers WHERE supplier_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, supplier_id);
            rs = pst.executeQuery();
            
            if(rs.next()){
                supplierName = rs.getString("name");
                supplierContact_person = rs.getString("contact_person");
                supplierPhone = rs.getString("phone");
                supplierEmail = rs.getString("email");
                supplierAddress = rs.getString("address");
            }
            
            name.setText(supplierName);
            contact_person.setText(supplierContact_person);
            phone.setText(supplierPhone);
            email.setText(supplierEmail);
            address.setText(supplierAddress);
            
            DefaultTableModel model = (DefaultTableModel) jt.getModel();
            model.addRow(new Object[]{supplier_id,supplierName,supplierContact_person,supplierPhone,supplierEmail,supplierAddress});
       }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex,"Message", JOptionPane.ERROR_MESSAGE);
       } 
    }

    @Override
    public void updateSupplier(
            String supplier_id,
            String supplierName, 
            String supplierContact_person, 
            String supplierPhone, 
            String supplierEmail, 
            String supplierAddress
    ){
        try{
            conn = connect();
            sql = "UPDATE suppliers SET name = ?, contact_person = ?, phone = ?, email = ?, address = ? WHERE supplier_id= ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, supplierName);
            pst.setString(2, supplierContact_person);
            pst.setString(3, supplierPhone);
            pst.setString(4, supplierEmail);
            pst.setString(5, supplierAddress);
            pst.setString(6, supplier_id);
            pst.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex,"Message", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    @Override
    public void removeSupplier(String supplier_id) {
       try{
           conn = connect();
           sql = "DELETE FROM suppliers WHERE supplier_id = ?";
           pst = conn.prepareStatement(sql);
           pst.setString(1, supplier_id);
           pst.executeUpdate();
       }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, ex,"Message", JOptionPane.ERROR_MESSAGE);
       }
       
    }

    //User Management
    @Override
    public void addUser(String username, String password, String role, String full_name) {
        try{
            conn = connect();
            sql = "INSERT INTO users(username,password,role,full_name) VALUES(?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, role);
            pst.setString(4, full_name);
            pst.executeUpdate();
        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex,"Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void searchUser(String user_id, JTextField username, JTextField password, JComboBox role, JTextField full_name, JTable jt) {
        try{
            conn = connect();
            sql = "SELECT * FROM users WHERE user_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, user_id);
            rs = pst.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) jt.getModel();
            model.setRowCount(0);
         
            if(rs.next()){
                username.setText(rs.getString("username"));
                password.setText(rs.getString("password"));
                role.setSelectedItem(rs.getString("role"));
                full_name.setText(rs.getString("full_name"));
                model.addRow(new Object[]{rs.getString("user_id"),rs.getString("username"),rs.getString("role"),rs.getString("full_name")});
                
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex,"Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String username_;
    public String password_;
    public String role_;
    public String full_name_;
    public String user_id_;
    
    
    @Override
    public void updateUser(JTextField user_id, JTextField username, JPasswordField password, JComboBox role, JTextField full_name) {
    
        try{
            user_id_ = user_id.getText();
            username_ = username.getText();
            password_ = password.getText();
            role_ = role.getSelectedItem().toString();
            full_name_ = full_name.getText();
            
            conn = connect();
            sql = "UPDATE user SET username=?,password=?,role=?,full_name=? WHERE user_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, username_);
            pst.setString(2, password_);
            pst.setString(3, role_);
            pst.setString(4, full_name_);
            pst.setString(5, user_id_);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex,"Message", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    @Override
    public void removeUser(String user_id) {
        try{
            conn = connect();
            sql = "DELETE FROM users WHERE user_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,user_id);
            pst.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex,"Message", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void selectMedTable(JTable jt){
        
        DefaultTableModel model = (DefaultTableModel)jt.getModel();
        model.setRowCount(0);
        try{
            
            con = connect();
            SQL = "SELECT * FROM medicines";
            PST = con.prepareStatement(SQL);
            RS = PST.executeQuery();
                       
            while(RS.next()){

                Medicine_id = RS.getString("medicine_id");
                Name = RS.getString("name");
                Company = RS.getString("company");
                Medicine_type = RS.getString("medicine_type");
                Price = Double.parseDouble(RS.getString("price"));
                Quantity_in_stock = Integer.parseInt(RS.getString("Quantity_in_stock"));
                Reorder_level = Integer.parseInt(RS.getString("reorder_level"));
                Expiry_date = RS.getString("expiry_date");
                Supplier_id = RS.getString("supplier_id");
                             
                model.addRow(new Object[]{Medicine_id, 
                Name, 
                Company, 
                Medicine_type, 
                String.valueOf(Price), 
                Quantity_in_stock, 
                Reorder_level, 
                Expiry_date, 
                Supplier_id}
                );
            }
        }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex,"Message", JOptionPane.ERROR_MESSAGE);
            }
    }
    
    public void selectSupplierTable(JTable jt){
       try{
           
            DefaultTableModel model = (DefaultTableModel) jt.getModel();
            model.setRowCount(0); 
            conn = connect();
            sql ="SELECT * FROM suppliers";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
                while(rs.next()){
                    supplier_id = rs.getString("supplier_id");
                    supplierName = rs.getString("name");
                    supplierContact_person = rs.getString("contact_person");
                    supplierPhone = rs.getString("phone");
                    supplierEmail = rs.getString("email");
                    supplierAddress = rs.getString("address");
                    model.addRow(new Object[]{supplier_id,supplierName,supplierContact_person,supplierPhone,supplierEmail,supplierAddress});
                }       
       }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex,"Message", JOptionPane.ERROR_MESSAGE);
       } 
    }
    public static String user_id;
    public static String username;
    public static String role;
    public static String full_name;
    
    public static void selectUserTable(JTable jt){
        try{
            con = connect();
            SQL ="SELECT * FROM users";
            PST = con.prepareStatement(SQL);
            RS = PST.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) jt.getModel();
            model.setRowCount(0);
            
            
            while(RS.next()){
                user_id = RS.getString("user_id");
                username = RS.getString("username");
                role = RS.getString("role");
                full_name = RS.getString("full_name");
                
                model.addRow(new Object[]{user_id,username,role,full_name});
            }
                    
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex,"Message", JOptionPane.ERROR_MESSAGE);
        
        }
    
    }
    
    public static void searchMed(
            String medID,
            JTable jt,
            JTextField name, 
            JTextField company, 
            JComboBox medicineType, 
            JTextField price, 
            JTextField soh, 
            JTextField supplierID, 
            JTextField reOrderLevel,
            JComboBox medDay,
            JComboBox medMonth,
            JComboBox medYear
    ){
        DefaultTableModel model = (DefaultTableModel)jt.getModel();
        model.addRow(new Object[]{});
        model.setRowCount(0);       
        try{
            con = connect();
            SQL = "SELECT * FROM medicines WHERE medicine_id=?";
            PST = con.prepareStatement(SQL);
            PST.setString(1, medID);
            RS = PST.executeQuery();
            if(RS.next()){
                Medicine_id = RS.getString("medicine_id");
                Name = RS.getString("name");
                Company = RS.getString("company");
                Medicine_type = RS.getString("medicine_type");
                Price = Double.parseDouble(RS.getString("price"));
                Quantity_in_stock = Integer.parseInt(RS.getString("Quantity_in_stock"));
                Reorder_level = RS.getInt("reorder_level");
                Expiry_date = RS.getString("expiry_date");
                Supplier_id = RS.getString("supplier_id");
            }
            model.addRow(new Object[]{Medicine_id, 
                Name, 
                Company, 
                Medicine_type, 
                String.valueOf(Price), 
                Quantity_in_stock, 
                Reorder_level, 
                Expiry_date, 
                Supplier_id}
                );
            
                year = Expiry_date.substring(0, 4);
                month = Expiry_date.substring(5, 7);
                day = Expiry_date.substring(8, 10);
                
                name.setText(Name);
                company.setText(Company);
                medicineType.setSelectedItem(Medicine_type);
                price.setText(String.valueOf(Price));
                soh.setText(String.valueOf(Quantity_in_stock));
                supplierID.setText(Supplier_id);
                reOrderLevel.setText(String.valueOf(Reorder_level));
                medDay.setSelectedItem(day);
                medMonth.setSelectedItem(month);
                medYear.setSelectedItem(year);
                        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex,"Message", JOptionPane.ERROR_MESSAGE);
        
        }
        
    }
}
