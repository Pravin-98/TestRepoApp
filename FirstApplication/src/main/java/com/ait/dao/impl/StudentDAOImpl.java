package com.ait.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.ait.dao.StudentDAO;
import com.ait.entity.Student;

public class StudentDAOImpl implements StudentDAO {

	SessionFactory factory;
	
	public StudentDAOImpl() {
	
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
		factory = metadata.getSessionFactoryBuilder().build();
	}
	
	@Override
	public void saveStudent(Student student) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.save(student);
			System.out.println("Student object is persisted in database");
			t.commit();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println("Student object is not persisted in database");
			System.out.println(e);
		}
		finally {
			session.close();
//			factory.close();
		}
		
	}

	@Override
	public void updateStudent(int sid, int marks) {
		
		Session session = factory.openSession();
		Student student = session.get(Student.class,sid);
		Transaction transaction = session.beginTransaction();
		
		try {
			student.setMarks(marks);
			session.update(student);
			
			transaction.commit();
			System.out.println("object is updated...");
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("object is not updated...");
		}
		finally {
			session.close();
		}
		
	}

	@Override
	public Student loadStudent(int sid) {
		Session session = factory.openSession();
		
		Student student = session.load(Student.class, sid);
				
		System.out.println("Name of Student :"+student.getSname());
		session.close();
		
		
		return student;
	}

	@Override
	public void deleteStudent(int sid) {
		
		Session session = factory.openSession();
		Student student = session.get(Student.class, sid);
		Transaction transaction = session.beginTransaction();
		
		try {
			session.delete(student);
			System.out.println("object is deleted...");
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("object is not deleted...");
			
		}
		finally {
			session.close();
		}
	}

	

}
