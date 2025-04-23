# StudentManagement

**🎓 Student Management System (Java, MySQL, JDBC)**

This is a Java-based console application designed to manage student records using MySQL as the backend database. The system allows users to add, view, update, and delete student data through a simple Command Line Interface (CLI). It demonstrates core concepts of JDBC, SQL operations, input validation, and error handling in a real-world database-connected environment.

**✅ Key Features**

**Add Student:** Insert new student records with name, email, and age (validated between 1–100).

**View Students:** Display all student records from the database in a clean format.

**Update Student:** Modify a student’s name, email, and age with full validation.

**Delete Student:** Remove student records using a unique student ID.

**Input Validation:** Ensures proper age entry and catches invalid numeric input.

**Exception Handling:** Catches and handles database connection or SQL execution errors.

**Modular Design:** Code is separated into clean methods for each operation.

**🛠️ Technologies Used**

**Programming Language:** Java

**Database:** MySQL

**Connectivity:** JDBC (Java Database Connectivity)

**SQL Features:** CRUD operations, PreparedStatement, input sanitization

**Tools:** IntelliJ / Eclipse, MySQL Workbench / phpMyAdmin

**🚀 How to Run the Project**

**1.Set Up the Database:**

CREATE DATABASE StudentDB;

USE StudentDB;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    email VARCHAR(100),
    age INT
);

**2.Update Credentials in Java File:**

private final String URL = "jdbc:mysql://localhost:3306/StudentDB";
private final String USER = "root";
private final String PASSWORD = "your_mysql_password";

**3.Compile & Run:**

javac StudentManager.java
java StudentManagement.StudentManager

**4.Use the Menu:**

Choose options 1–4 to manage students
Press 5 to exit


**Thank you:)**
