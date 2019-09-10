package com.vinegrad.dosecurity.repository;

import org.springframework.data.repository.CrudRepository;

import com.vinegrad.dosecurity.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsernameIn(String username);
}
