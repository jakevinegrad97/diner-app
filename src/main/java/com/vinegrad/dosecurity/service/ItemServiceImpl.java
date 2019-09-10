package com.vinegrad.dosecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinegrad.dosecurity.model.Item;
import com.vinegrad.dosecurity.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository repository;
	
	@Override
	public boolean addItem(Item item) {
		repository.save(item);
		return true;
	}

	@Override
	public List<Item> findByMenu(String menu) {
		return repository.findByMenuIn(menu);
	}

	@Override
	public boolean deleteItem(Long id) {
		repository.deleteById(id);
		return true;
	}

	@Override
	public Optional<Item> findItemById(Long id) {
		Item result = repository.findById(id).get();
		return Optional.ofNullable(result);
	}
	
	@Override
	public boolean updateItem(Item oldItem, Item newItem) {
		repository.delete(oldItem);
		repository.save(newItem);
		return true;
	}
}
