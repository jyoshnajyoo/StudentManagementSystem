
package com.student.ui;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.student.model.Student;
import com.student.service.StudentService;
import com.student.util.HibernateUtil;

public class StudentManagementSystemApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        StudentService service = new StudentService();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student by ID");
            System.out.println("6. Exit");

            int choice = readNumber("Enter your choice: ", 1, 6);

            switch (choice) {

            case 1: {

                System.out.println("\n----- Add Student -----");

                String name = readText("Enter Student Name: ");
                int age = readNumber("Enter Age: ", 1, 100);
                String email = readEmail(service, 0);
                String course = readText("Enter Course: ");
                BigDecimal marks = readMarks();

                Student student = new Student(
                        name, age, email, course, marks);

                service.addStudent(student);

                break;
            }

            case 2: {

                System.out.println("\n----- Student Details -----");

                List<Student> students = service.getAllStudents();

                if (students.isEmpty()) {

                    System.out.println("No students found!");

                } else {

                    for (Student student : students) {

                        System.out.println("Student ID: " + student.getStudentId());
                        System.out.println("Name: " + student.getName());
                        System.out.println("Age: " + student.getAge());
                        System.out.println("Email: " + student.getEmail());
                        System.out.println("Course: " + student.getCourse());
                        System.out.println("Marks: " + student.getMarks());
                        System.out.println("-------------------------");
                    }
                }

                break;
            }

            case 3: {

                System.out.println("\n----- Update Student -----");

                int updateId = readNumber("Enter Student ID: ", 1, 100000);

                Student existingStudent = service.getStudentById(updateId);

                if (existingStudent != null) {

                    System.out.println("Enter New Details");

                    String name = readText("Enter Student Name: ");
                    int age = readNumber("Enter Age: ", 1, 100);
                    String email = readEmail(service, updateId);
                    String course = readText("Enter Course: ");
                    BigDecimal marks = readMarks();

                    Student updatedStudent = new Student(
                            name, age, email, course, marks);

                    service.updateStudent(updateId, updatedStudent);

                } else {

                    System.out.println("Student not found!");
                }

                break;
            }

            case 4: {

                System.out.println("\n----- Delete Student -----");

                int deleteId = readNumber("Enter Student ID: ", 1, 100000);

                Student deleteStudent = service.getStudentById(deleteId);

                if (deleteStudent != null) {

                    System.out.println("Student Name: " + deleteStudent.getName());

                    System.out.println("Are you sure you want to delete this student?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");

                    int confirm = readNumber("Enter your choice: ", 1, 2);

                    if (confirm == 1) {

                        service.deleteStudent(deleteId);

                    } else {

                        System.out.println("Student deletion cancelled!");
                    }

                } else {

                    System.out.println("Student not found!");
                }

                break;
            }

            case 5: {

                System.out.println("\n----- Search Student -----");

                int searchId = readNumber("Enter Student ID: ", 1, 100000);

                Student searchStudent = service.getStudentById(searchId);

                if (searchStudent != null) {

                    System.out.println("\nStudent Details");
                    System.out.println("-------------------------");
                    System.out.println("Student ID: " + searchStudent.getStudentId());
                    System.out.println("Name: " + searchStudent.getName());
                    System.out.println("Age: " + searchStudent.getAge());
                    System.out.println("Email: " + searchStudent.getEmail());
                    System.out.println("Course: " + searchStudent.getCourse());
                    System.out.println("Marks: " + searchStudent.getMarks());
                    System.out.println("-------------------------");

                } else {

                    System.out.println("Student not found!");
                }

                break;
            }

            case 6:

                System.out.println("Thank you for using Student Management System!");

                HibernateUtil.shutdown();

                sc.close();

                return;
            }
        }
    }

    // READ NUMBER
    public static int readNumber(String message, int min, int max) {

        while (true) {

            System.out.print(message);

            try {

                int number = Integer.parseInt(sc.nextLine());

                if (number >= min && number <= max) {
                    return number;
                }

                System.out.println("Please enter a number between " + min + " and " + max);

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number!");
            }
        }
    }

    // READ TEXT
    public static String readText(String message) {

        while (true) {

            System.out.print(message);

            String value = sc.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty!");
        }
    }

    // READ EMAIL
    public static String readEmail(StudentService service, int studentId) {

        while (true) {

            System.out.print("Enter Email: ");

            String email = sc.nextLine().trim();

            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {

                boolean emailUsed;

                if (studentId == 0) {

                    emailUsed = service.isEmailAlreadyUsed(email);

                } else {

                    emailUsed = service.isEmailUsedByOtherStudent(email, studentId);
                }

                if (!emailUsed) {
                    return email;
                }

                System.out.println("Email already exists! Enter another email.");

            } else {

                System.out.println("Please enter a valid email!");
            }
        }
    }

    // READ MARKS
    public static BigDecimal readMarks() {

        while (true) {

            System.out.print("Enter Marks (0-100): ");

            try {

                BigDecimal marks = new BigDecimal(sc.nextLine());

                if (marks.compareTo(BigDecimal.ZERO) >= 0
                        && marks.compareTo(new BigDecimal("100")) <= 0) {

                    return marks;
                }

                System.out.println("Marks must be between 0 and 100!");

            } catch (NumberFormatException e) {

                System.out.println("Please enter valid marks!");
            }
        }
    }
}
