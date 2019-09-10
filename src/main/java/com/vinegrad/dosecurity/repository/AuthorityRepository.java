package com.vinegrad.dosecurity.repository;

import org.springframework.data.repository.CrudRepository;

import com.vinegrad.dosecurity.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
}
