package com.ait.dao;

import com.ait.entity.Student;

public interface StudentDAO {
	
	void saveStudent(Student student);
	void updateStudent(int sid , int marks);
	Student loadStudent(int sid);
	void deleteStudent(int sid);
}
