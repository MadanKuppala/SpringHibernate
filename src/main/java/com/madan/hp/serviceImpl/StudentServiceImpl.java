package com.madan.hp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madan.hp.dao.StudentDao;
import com.madan.hp.model.Student;
import com.madan.hp.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentDao stuDao;

	@Override
	public List<Student> getStudentList() {
		
		return stuDao.getStudentList();
	}

	@Override
	public Student submitDetails(Student student) {
		
		return stuDao.submitDetails(student);
	}

}
