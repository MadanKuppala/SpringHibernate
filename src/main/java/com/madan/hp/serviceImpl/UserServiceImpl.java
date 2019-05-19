package com.madan.hp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madan.hp.dao.UserDao;
import com.madan.hp.model.IntranetUser;
import com.madan.hp.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	public UserDao userDao;
	
	public IntranetUser findById(String userId) {
		return userDao.findById(userId);
	}
	

}
