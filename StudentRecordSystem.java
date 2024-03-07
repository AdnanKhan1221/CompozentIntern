package com;

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int age;
    private double gpa;
    private int attendance;

    public Student(String name, int age, double gpa, int attendance) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
        this.attendance = attendance;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }

    public int getAttendance() {
        return attendance;
    }

    public void updateDetails(int age, double gpa, int attendance) {
        this.age = age;
        this.gpa = gpa;
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", GPA: " + gpa + ", Attendance: " + attendance;
    }
}

public class StudentRecordSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nStudent Record System");
            System.out.println("1. Add Student");
            System.out.println("2. Display Student Details");
            System.out.println("3. Update Student Details");
            System.out.println("4. List All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudentDetails();
                    break;
                case 3:
                    updateStudentDetails();
                    break;
                case 4:
                    listAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        System.out.print("Enter student GPA: ");
        double gpa = scanner.nextDouble();
        System.out.print("Enter student attendance: ");
        int attendance = scanner.nextInt();

        Student student = new Student(name, age, gpa, attendance);
        students.add(student);
        System.out.println("Student added successfully.");
    }

    private static void displayStudentDetails() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void updateStudentDetails() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                System.out.print("Enter new age: ");
                int age = scanner.nextInt();
                System.out.print("Enter new GPA: ");
                double gpa = scanner.nextDouble();
                System.out.print("Enter new attendance: ");
                int attendance = scanner.nextInt();
                student.updateDetails(age, gpa, attendance);
                System.out.println("Student details updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("List of all students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}