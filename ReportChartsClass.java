/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.healthfirst.models;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author zweli
 */
public final class ReportChartsClass {
    //getDataset()
  
    //getBarChart
    private static String chartTitle;
    private static String xTitle;
    private static String yTitle;
    
    
    public static JFreeChart getBarChart(DefaultCategoryDataset dataset){
        JFreeChart barChart = ChartFactory.createBarChart(
        chartTitle,
        xTitle,
        yTitle,
        dataset
        );
        
        return barChart;
    }
    
    public static void getChartContainer(JPanel jp, String chartTitle, String xTitle, String yTitle,DefaultCategoryDataset dataset){
        ReportChartsClass.chartTitle = chartTitle;
        ReportChartsClass.xTitle = xTitle;
        ReportChartsClass.yTitle = yTitle;
        
        
        ChartPanel chartPanel = new ChartPanel(getBarChart(dataset));
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        
        jp.setLayout(new java.awt.BorderLayout());
        jp.add(chartPanel, BorderLayout.CENTER);
        jp.validate();
    }
    
    public static void getChartContainer(JPanel jp, String chartTitle, String xTitle, String yTitle,DefaultPieDataset dataset){
        ReportChartsClass.chartTitle = chartTitle;
        ReportChartsClass.xTitle = xTitle;
        ReportChartsClass.yTitle = yTitle;
        
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        pieDataset.setValue("Paracetamol", 50);
        pieDataset.setValue("Ibuprofen", 30);

//        JFreeChart pieChart = ChartFactory.createPieChart(
//                "Sales Distribution",
//                pieDataset,
//                true, true, false
//        );
//        
        //jp.setLayout(new java.awt.BorderLayout());
        //jp.add(chartPanel, BorderLayout.CENTER);
        //jp.validate();
    }
    
    
    
//    public static void testGetChartContainer(JPanel jp) {
//        
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        
//      //dataset.setValue(yTotalQtySold, legend, xSoldItems);
//        dataset.setValue(yTotalRandSales, "Sales", xSoldItems);
//        
//        ChartPanel chartPanel = new ChartPanel(getBarChart(dataset));
//        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
//
//        jp.setLayout(new java.awt.BorderLayout());
//        jp.add(chartPanel, BorderLayout.CENTER);
//        jp.validate();
//    }
    
 
    
    
    
}
