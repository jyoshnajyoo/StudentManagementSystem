# Student Management System

A simple Java-based Student Management System developed using Hibernate and MySQL. This console application helps manage student records with basic CRUD operations.

## Technologies Used

- Java
- Hibernate ORM
- MySQL
- Maven
- Eclipse IDE

## Features

- Add a new student
- View all students
- Search student by ID
- Update student details
- Delete student records
- Validate user inputs
- Check for duplicate email addresses

## Project Structure

```text
StudentManagementSystem
│
├── src/main/java
│   └── com.student
│       ├── model
│       │   └── Student.java
│       ├── dao
│       │   └── StudentDao.java
│       ├── service
│       │   └── StudentService.java
│       ├── ui
│       │   └── StudentManagementSystemApp.java
│       └── util
│           └── HibernateUtil.java
│
├── src/main/resources
│   └── hibernate.cfg.xml.example
│
├── pom.xml
└── README.md
```

## Database Setup

1. Create a MySQL database named `student_db`.
2. Configure your local database connection in `hibernate.cfg.xml`.
3. Keep your database username and password private.
4. Hibernate creates or updates the required table when the application runs.

## How to Run

1. Clone or download this repository.
2. Import the project into Eclipse as an existing Maven project.
3. Update Maven dependencies.
4. Configure your local Hibernate database connection.
5. Open `StudentManagementSystemApp.java`.
6. Run the application as a Java Application.

## Learning Outcomes

- Understanding Java object-oriented programming
- Working with Hibernate ORM
- Connecting Java applications to MySQL
- Implementing CRUD operations
- Understanding DAO and Service layers
- Handling user input and validation

## Project Type

Console-based Java application.