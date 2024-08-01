package com.hibernate.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Student;
import com.hibernate.util.HibernateUtil;


public class StudentDao implements IstudentDao {
	
	//saveStudent
	//getAllStudent
	//getStudentbyId
	//UpdateStudent
	//DeleteStudent
	
	
	@Override
	public void saveStudent(Student student) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			//start the transaction
			transaction  =	session.beginTransaction();
			
			//save Student object
			session.save(student);
			
			//commit the transction
			transaction.commit();	
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}	
	
	@Override
	public void updateStudent(Student student) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			//start the transaction
			transaction  =	session.beginTransaction();
			
			//update Student object
			session.saveOrUpdate(student);
			
			//commit the transction
			transaction.commit();	
		}catch(Exception e) {
			e.printStackTrace();
	
		}
	}	
	@Override
	public Student getStudentById(long id) {
		Transaction transaction = null;
		
		Student student = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			//start the transaction
			transaction  =	session.beginTransaction();
			
			//get Student object
			student = session.get(Student.class, id);
			//student = session.load(Student.class, id);

			//commit the transction
			transaction.commit();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return student;
	}	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Student> getAllStudent() {
		Transaction transaction = null;
		
		List<Student> students = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			//start the transaction
			transaction  =	session.beginTransaction();
			
			//get Students
			students = session.createQuery("from Student").list();
			

			//commit the transction
			transaction.commit();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return students;
	}

	@Override
	public void deleteStudent(long id) {
		Transaction transaction = null;
		Student student = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			//start the transaction
			transaction  =	session.beginTransaction();
			
			//delete Student object
			student =  session.get(Student.class, id);
			session.delete(student);
			
			//commit the transction
			
			transaction.commit();	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}		

}

