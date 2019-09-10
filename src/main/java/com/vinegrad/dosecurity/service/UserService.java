package com.vinegrad.dosecurity.service;

import com.vinegrad.dosecurity.model.User;

public interface UserService {
	
	boolean saveUser(User user);

	User findByUsername(String username);

}