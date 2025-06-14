# Inventory Management System

## Overview
This Java-based Inventory Management System allows users to manage product records efficiently through a GUI interface connected to a MySQL database. It includes functionality for adding, viewing, updating, deleting, and searching inventory items.

## Features
- **CRUD Operations:** Add, view, update, and delete product entries.
- **Search Function:** Filter records using a keyword with SQL `LIKE`.
- **GUI Interface:** Built with Java Swing (`JTable`, `JButton`, `JTextField`, etc.).
- **MySQL Integration:** Connected using `mysql-connector-java` through JDBC.
- **OOP Principles:** Modular structure using classes like `Product`, `ProductDAO`, and `DBConnection`.

## Technologies Used
- Java (JDK 8 or higher)
- Java Swing (for GUI)
- MySQL
- JDBC (MySQL Connector)

## Database Schema
```sql
CREATE TABLE inventory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    category VARCHAR(50),
    quantity INT,
    price DOUBLE
);
```
Project Structure
Product.java: Model class for inventory items.

ProductDAO.java: Data access object for CRUD operations.

DBConnection.java: Handles MySQL connectivity.

InventoryGUI.java: Main GUI frame for user interaction.

How to Run
Clone this repository:

bash
Copy
Edit
git clone https://github.com/lmaetrix/Final-project-CPE121.git
Open the project in NetBeans or any Java IDE.

Ensure MySQL is installed and running.

Import the required MySQL JDBC driver (mysql-connector-java).

Create the inventory table using the schema above.

Run InventoryGUI.java to launch the application.
