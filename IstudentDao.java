package com.hibernate.dao;

import java.util.List;

import com.hibernate.model.Student;

public interface IstudentDao {

	void saveStudent(Student student);

	void updateStudent(Student student);

	Student getStudentById(long id);

	List<Student> getAllStudent();

	void deleteStudent(long id);

}