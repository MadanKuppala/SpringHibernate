package com.madan.hp.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.madan.hp.dao.UserDao;
import com.madan.hp.model.IntranetUser;


@Repository
@Transactional
@SuppressWarnings("unchecked")
public class UserDaoImpl implements UserDao {
	
	@Qualifier("mySessionFactory")
	@Autowired private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	 

	public IntranetUser findById(String userId) {
		try {
			IntranetUser instance = (IntranetUser) getSession().get(
					"com.madan.hp.model.IntranetUser", userId);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
}