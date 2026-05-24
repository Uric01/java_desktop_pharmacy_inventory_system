# 🏥 HealthFirst Pharmacy Inventory Management System

# User Manual


## 📌 Overview

HealthFirst is a desktop-based pharmacy inventory management system designed to manage medicines, suppliers, and sales transactions. 
The system supports role-based access for **Admin** and **Cashier** users.


## ⚙️ System Requirements

* Windows OS
* Java Runtime Environment (JRE 8 or above)
* XAMPP (MySQL must be running)


## 🚀 Installation & Setup

### Step 1: Install XAMPP

1. Download and install XAMPP
2. Open XAMPP Control Panel
3. Start:

   * Apache
   * MySQL

## Step 2: Download jre and lib folders and save them in the local folder of zwelabantu_mashego_pims.exe from the following link

   * jre: https://drive.google.com/drive/folders/1aLFez7M0nM-d_-sKSHtdIxb1djxANkS9?usp=sharing
   * lib: https://drive.google.com/drive/folders/1rHAsecDXQ5Z_SB51NqFk27f2oDkP_gsu?usp=sharing


### Step 3: Import Database

1. Open browser → `http://localhost/phpmyadmin`
2. Click **Import**
3. Select `pharmacy_db.sql` file
4. Click **Go**


### Step 4: Run the Application

1. Open the application folder
2. Double-click: zwelabantu_mashego_pims.exe


## 🔐 Login

Use the default admin account:

Username: admin
Password: admin123


## 🧭 System Navigation

### 👤 Admin Role

Admins have full access to:

* Manage Medicines
* Manage Suppliers
* View Reports
* Monitor Stock Levels


### 💳 Cashier Role

Cashiers can:

* Process sales (Point of Sale)
* Generate bills
* View available medicines


## 💊 Medicines Management

* Add new medicines
* Update existing medicine details
* Delete medicines
* Monitor stock levels


## 🚚 Supplier Management

* Add supplier details
* Update supplier information
* Delete supplier


## 🧾 Billing System

* Add items to cart
* Enter quantity
* Calculate total automatically
* Generate bill
* Print or save receipt


## 📊 Reports & Insights

The system provides:

* Medicine expiry alert
* Low stock alerts
* Sales revenue
* Sales quantity


## ⚠️ Important Notes

* Ensure **MySQL is running** before opening the application
* Do not close XAMPP while the system is running
* Database must be imported before first use
* Ensure jre and lib folders, as per step 2 are saved in the same local folder as where zwelabantu_mashego_pims.exe is located


## ❗ Troubleshooting

### Application does not start

* Ensure Java is installed
* Ensure `.exe` file is not blocked
* Ensure jre and lib folders are downloaded as per step 2


### Database connection error

* Check XAMPP MySQL is running
* Verify database is imported


### Login not working

* Confirm correct username/password
* Check database connection


## 🔒 Security Notes

* Change default admin credentials after first login
* Do not share database access details


## 👨‍💻 Developed By

Zwelabantu Cecil Mashego 



## 📅 Version

1.0
