/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.assignment.healthfirst.models;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zweli
 */
public interface IReportGeneration {
 
    public void salesReports(JPanel jp, JPanel jpQty);
   //    Daily / weekly / monthly sales
   //    Top-selling products
   //    Slow-moving products
   //    Revenue trends
    public void lowStockReorderReports(DefaultTableModel model);
   //    Items below reorder level
   //    Suggested reorder quantity
   //    Frequency of stockouts
    public void expiryBatchReports(DefaultTableModel model);
   //    Items nearing expiry
   //    Expired stock
   //    Batch tracking    
    public void userCashierReports();
   //    Sales per cashier
   //    Transactions per user
   //    Error/void transactions

    public void inventoryMovementReports();
   //    Stock-in (purchases)
   //    Stock-out (sales)
   //    Returns
   //    Adjustments (damaged/expired)
    public void profitMarginReports();
   //    Cost price vs selling price
   //    Profit per product
   //    Total profit over time
   //    High-margin vs low-margin items

}
