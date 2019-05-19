package com.madan.hp.dao;

import java.util.List;

import com.madan.hp.model.Student;

public interface StudentDao {

	public List<Student> getStudentList();

	public Student submitDetails(Student student);

}
