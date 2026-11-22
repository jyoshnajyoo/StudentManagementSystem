
package com.student.service;

import java.util.List;

import com.student.dao.StudentDao;
import com.student.model.Student;

public class StudentService {

    private final StudentDao studentDao = new StudentDao();

    // INSERT STUDENT
    public void addStudent(Student student) {

        studentDao.saveStudent(student);
    }

    // VIEW ALL STUDENTS
    public List<Student> getAllStudents() {

        return studentDao.getAllStudents();
    }

    // GET STUDENT BY ID
    public Student getStudentById(int studentId) {

        return studentDao.getStudentById(studentId);
    }

    // CHECK DUPLICATE EMAIL
    public boolean isEmailAlreadyUsed(String email) {

        return studentDao.isEmailAlreadyUsed(email);
    }

    // CHECK EMAIL WHILE UPDATING
    public boolean isEmailUsedByOtherStudent(String email, int studentId) {

        return studentDao.isEmailUsedByOtherStudent(email, studentId);
    }

    // UPDATE STUDENT
    public void updateStudent(int studentId, Student student) {

        studentDao.updateStudent(studentId, student);
    }

    // DELETE STUDENT
    public void deleteStudent(int studentId) {

        studentDao.deleteStudent(studentId);
    }
}
