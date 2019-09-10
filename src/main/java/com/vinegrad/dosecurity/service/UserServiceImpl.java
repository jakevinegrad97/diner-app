package com.vinegrad.dosecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.vinegrad.dosecurity.model.Authority;
import com.vinegrad.dosecurity.model.User;
import com.vinegrad.dosecurity.repository.AuthorityRepository;
import com.vinegrad.dosecurity.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository uRepository;
	@Autowired
	private AuthorityRepository aRepository;
	
	protected String bcryptMyPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashed = encoder.encode(password);
		return hashed;
	}
	
	@Override
	public boolean saveUser(User user) {
		user.setPassword(bcryptMyPassword(user.getPassword()));
		uRepository.save(user);
		aRepository.save(new Authority(user.getUsername(),"ROLE_USER"));
		return true;
		
	}
	
	@Override
	public User findByUsername(String username) {
		return uRepository.findByUsernameIn(username);
	}
	
}
