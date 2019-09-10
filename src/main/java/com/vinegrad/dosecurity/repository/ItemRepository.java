package com.vinegrad.dosecurity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vinegrad.dosecurity.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
	List<Item> findByMenuIn(String menu);
}
