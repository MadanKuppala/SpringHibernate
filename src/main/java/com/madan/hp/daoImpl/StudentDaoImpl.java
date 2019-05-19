package com.madan.hp.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.madan.hp.dao.StudentDao;
import com.madan.hp.model.Student;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class StudentDaoImpl implements StudentDao {
	
	@Autowired
	SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Student> getStudentList() {
		String hql = "from Student";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Student submitDetails(Student student) {
		getSession().save(student);
		return student;
	}
	
	

}
