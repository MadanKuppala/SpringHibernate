package com.madan.hp.service;

import java.util.List;

import com.madan.hp.model.Student;

public interface StudentService {

	List<Student> getStudentList();

	Student submitDetails(Student student);

}
