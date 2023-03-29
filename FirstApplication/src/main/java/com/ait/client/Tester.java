package com.ait.client;

import com.ait.dao.StudentDAO;
import com.ait.dao.impl.StudentDAOImpl;
import com.ait.entity.Student;

public class Tester {

	public static void main(String[] args) {
		
		StudentDAO dao = new StudentDAOImpl();
		
		// Creating Student object
		
		Student student = new Student();
		
		student.setSid(103);
		student.setSname("ganesh");
		student.setGender("male");
		student.setMarks(800);
		
	//	dao.saveStudent(student); 
		
		dao.updateStudent(103, 700);

	}

}
