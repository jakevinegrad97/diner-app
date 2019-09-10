package com.vinegrad.dosecurity.service;

import java.util.List;
import java.util.Optional;

import com.vinegrad.dosecurity.model.Item;

public interface ItemService {

	boolean addItem(Item item);
	
	List<Item> findByMenu(String menu);
	
	boolean deleteItem(Long id);
	
	Optional<Item> findItemById(Long id);

	boolean updateItem(Item oldItem, Item newItem);

}