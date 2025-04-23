package StudentManagement;

import java.sql.*;
import java.util.Scanner;

public class StudentManager {
    private final String URL = "jdbc:mysql://localhost:3306/StudentDB";
    private final String USER = "root";
    private final String PASSWORD = "54321";
    private Connection conn;
    private Scanner scanner;

    public StudentManager() {
        scanner = new Scanner(System.in);
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
    }

    public void start() {
        int choice;
        do {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter age: ");
        int age;
        while (true) {
            System.out.print("Enter age (1-100): ");
            age = getIntInput();
            if (age >= 1 && age <= 100) {
                break;
            } else {
                System.out.println("❌ Invalid age. Please enter a valid age between 1 and 100.");
            }
        }
        String sql = "INSERT INTO students (name, email, age) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, age);
            pstmt.executeUpdate();
            System.out.println("Student added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void viewStudents() {
        String sql = "SELECT * FROM students";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                                   rs.getString("name") + " | " +
                                   rs.getString("email") + " | " +
                                   rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = getIntInput();

        scanner.nextLine(); // Clear the buffer
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();

        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();

        int newAge;
        while (true) {
            System.out.print("Enter new age: ");
            newAge = getIntInput();
            if (newAge >= 10 && newAge <= 100) {
                break;
            } else {
                System.out.println("❌ Invalid age. Please enter a valid age between 10 and 120.");
            }
        }

        String sql = "UPDATE students SET name = ?, email = ?, age = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, newEmail);
            pstmt.setInt(3, newAge);
            pstmt.setInt(4, id);

            int updated = pstmt.executeUpdate();
            System.out.println(updated > 0 ? "✅ Student record updated successfully." : "⚠️ Student not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Invalid input. Please enter a valid number: ");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }

	private void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int deleted = pstmt.executeUpdate();
            System.out.println(deleted > 0 ? "Student deleted." : "Student not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

