package com.madan.hp.dao;

import com.madan.hp.model.IntranetUser;
import com.madan.hp.model.User;

public interface UserDao {

	IntranetUser findById(String userId);

}