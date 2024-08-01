package com.hibernate;

import java.util.List;

import com.hibernate.dao.IstudentDao;
import com.hibernate.dao.StudentDao;
import com.hibernate.model.Student;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        IstudentDao studentDao = new StudentDao();

        // Test saveStudent

        Student student1 = new Student("Purna", "Parkar", "purnap10@gmail.com");
        studentDao.saveStudent(student1);
        System.out.println("Saved student with ID: " + student1.getId());
        
        Student student2 = new Student("Rihan", "Parkar", "rihanp10@gmail.com");
        studentDao.saveStudent(student2);
        System.out.println("Saved student with ID: " + student2.getId());
       
        Student student3 = new Student("Sagar", "Parkar", "sagarp10@gmail.com");
        studentDao.saveStudent(student3);
        System.out.println("Saved student with ID: " + student3.getId());
        
        // Test updateStudent
        student1.setFirstname("Vaibhav");
        studentDao.updateStudent(student1);
        System.out.println("Updated student with ID: " + student1.getId());

        // Test getStudentById
        Student studen2 = studentDao.getStudentById(student1.getId());
        if (studen2 != null) {
            System.out.println("Fetched student: " + student1.getFirstname() + " " + student2.getLastname() + " (" + student2.getEmail() + ")");
        } else {
            System.out.println("No student found with ID: " + student1.getId());
        }

        // Test getAllStudents
        List<Student> students = studentDao.getAllStudent();
        System.out.println("All students:");
        students.forEach(stu -> System.out.println(stu.getId() + ": " + stu.getFirstname() + " " + stu.getLastname() + " (" + stu.getEmail() + ")"));

        // Test deleteStudent
        studentDao.deleteStudent(student1.getId());
        System.out.println("Deleted student with ID: " + student1.getId());

        // Verify deletion
        Student deletedStudent = studentDao.getStudentById(student1.getId());
        if (deletedStudent == null) {
            System.out.println("Student with ID " + student1.getId() + " has been successfully deleted.");
        } else {
            System.out.println("Failed to delete student with ID: " + student1.getId());
        }
        
        studentDao.deleteStudent(student2.getId());
        System.out.println("Deleted student with ID: " + student2.getId());
        
        Student deletedStudent2 = studentDao.getStudentById(student2.getId());
        if (deletedStudent2 == null) {
            System.out.println("Student with ID " + student2.getId() + " has been successfully deleted.");
        } else {
            System.out.println("Failed to delete student with ID: " + student2.getId());
        }
    }
}
