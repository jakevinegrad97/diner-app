package com.vinegrad.dosecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@Table(name="items")
@ToString
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long itemId;
	@Column(name="name")
	private String name;
	@Column(name="menu")
	private String menu;
	@Column(name="price")
	private double price;
	@Column(name="description")
	private String description;
	@Column(name="image")
	private String image;
	
	public Item(String name, String menu, double price, String description, String image) {
		super();
		this.name = name;
		this.menu = menu;
		this.price = price;
		this.description = description;
		this.image = image;
	}
	
	
}
