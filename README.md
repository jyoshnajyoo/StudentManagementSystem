# Student Management System

A simple console-based Student Management System developed using Java, Hibernate, and MySQL. This application allows users to manage student records through basic CRUD operations.

## Technologies Used

- Java 21
- Hibernate ORM 6.6
- MySQL 8
- Maven
- Jakarta Persistence API

## Features

- Add a new student
- View all students
- Search student by ID
- Update student details
- Delete student with confirmation
- Validate student inputs
- Check duplicate email addresses
- Store student records in MySQL database

## Project Structure

```text
StudentManagementSystem
├── src
│   └── main
│       ├── java
│       │   └── com.student
│       │       ├── dao
│       │       ├── model
│       │       ├── service
│       │       ├── ui
│       │       └── util
│       └── resources
│           ├── hibernate.cfg.xml
│           └── hibernate.cfg.xml.example
├── .gitignore
├── pom.xml
└── README.md
```

## Database Setup

1. Install MySQL.
2. Create a database:

```sql
CREATE DATABASE student_db;
```

3. Copy `hibernate.cfg.xml.example` and rename the copy to `hibernate.cfg.xml`.
4. Update the MySQL username and password in `hibernate.cfg.xml`.
5. Hibernate automatically creates or updates the required table.

## How to Run

1. Clone this repository.
2. Open the project in Eclipse or IntelliJ IDEA.
3. Update the database credentials.
4. Reload Maven dependencies.
5. Run `StudentManagementSystemApp.java`.

## Author

Jyoshna Karanam