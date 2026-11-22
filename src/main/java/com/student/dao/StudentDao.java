
package com.student.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.student.model.Student;
import com.student.util.HibernateUtil;

public class StudentDao {

    // INSERT STUDENT
    public void saveStudent(Student student) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            session.persist(student);

            transaction.commit();

            System.out.println("Student saved successfully!");

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {

            session.close();
        }
    }

    // VIEW ALL STUDENTS
    public List<Student> getAllStudents() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            List<Student> students = session
                    .createQuery("from Student", Student.class)
                    .list();

            return students;

        } finally {

            session.close();
        }
    }

    // GET STUDENT BY ID
    public Student getStudentById(int studentId) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            Student student = session.get(Student.class, studentId);

            return student;

        } finally {

            session.close();
        }
    }

    // CHECK DUPLICATE EMAIL
    public boolean isEmailAlreadyUsed(String email) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            Long count = session.createQuery(
                    "select count(s) from Student s where s.email = :email",
                    Long.class)
                    .setParameter("email", email)
                    .getSingleResult();

            return count > 0;

        } finally {

            session.close();
        }
    }

    // CHECK EMAIL WHILE UPDATING
    public boolean isEmailUsedByOtherStudent(String email, int studentId) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            Long count = session.createQuery(
                    "select count(s) from Student s where s.email = :email and s.studentId != :id",
                    Long.class)
                    .setParameter("email", email)
                    .setParameter("id", studentId)
                    .getSingleResult();

            return count > 0;

        } finally {

            session.close();
        }
    }

    // UPDATE STUDENT
    public void updateStudent(int studentId, Student updatedStudent) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            Student student = session.get(Student.class, studentId);

            if (student != null) {

                student.setName(updatedStudent.getName());
                student.setAge(updatedStudent.getAge());
                student.setEmail(updatedStudent.getEmail());
                student.setCourse(updatedStudent.getCourse());
                student.setMarks(updatedStudent.getMarks());

                System.out.println("Student updated successfully!");

            } else {

                System.out.println("Student not found!");
            }

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {

            session.close();
        }
    }

    // DELETE STUDENT
    public void deleteStudent(int studentId) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            Student student = session.get(Student.class, studentId);

            if (student != null) {

                session.remove(student);

                System.out.println("Student deleted successfully!");

            } else {

                System.out.println("Student not found!");
            }

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {

            session.close();
        }
    }
}
