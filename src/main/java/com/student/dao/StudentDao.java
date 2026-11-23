
package com.student.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.student.model.Student;
import com.student.util.HibernateUtil;

public class StudentDao {

    // INSERT STUDENT
    public void saveStudent(Student student) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(student);

            transaction.commit();

            System.out.println("Student saved successfully!");

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw new RuntimeException(
                    "Unable to save student", e);
        }
    }

    // VIEW ALL STUDENTS
    public List<Student> getAllStudents() {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "from Student", Student.class)
                    .list();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to retrieve students", e);
        }
    }

    // GET STUDENT BY ID
    public Student getStudentById(int studentId) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.get(Student.class, studentId);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to find student", e);
        }
    }

    // CHECK DUPLICATE EMAIL
    public boolean isEmailAlreadyUsed(String email) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                    "select count(s) from Student s where s.email = :email",
                    Long.class)
                    .setParameter("email", email)
                    .getSingleResult();

            return count > 0;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to check email", e);
        }
    }

    // CHECK EMAIL WHILE UPDATING
    public boolean isEmailUsedByOtherStudent(
            String email, int studentId) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                    "select count(s) from Student s " +
                    "where s.email = :email and s.studentId != :id",
                    Long.class)
                    .setParameter("email", email)
                    .setParameter("id", studentId)
                    .getSingleResult();

            return count > 0;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to check student email", e);
        }
    }

    // UPDATE STUDENT
    public void updateStudent(
            int studentId, Student updatedStudent) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Student student =
                    session.get(Student.class, studentId);

            if (student != null) {

                student.setName(updatedStudent.getName());
                student.setAge(updatedStudent.getAge());
                student.setEmail(updatedStudent.getEmail());
                student.setCourse(updatedStudent.getCourse());
                student.setMarks(updatedStudent.getMarks());

                transaction.commit();

                System.out.println("Student updated successfully!");

            } else {

                transaction.commit();

                System.out.println("Student not found!");
            }

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw new RuntimeException(
                    "Unable to update student", e);
        }
    }

    // DELETE STUDENT
    public void deleteStudent(int studentId) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Student student =
                    session.get(Student.class, studentId);

            if (student != null) {

                session.remove(student);

                transaction.commit();

                System.out.println("Student deleted successfully!");

            } else {

                transaction.commit();

                System.out.println("Student not found!");
            }

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw new RuntimeException(
                    "Unable to delete student", e);
        }
    }
}
