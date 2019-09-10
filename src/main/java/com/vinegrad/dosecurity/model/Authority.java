package com.vinegrad.dosecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="authorities")
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long authorityId;
	@Column(name="username")
	private String username;
	@Column(name="authority")
	private String authority;
	
	public Authority(String username, String authority) {
		super();
		this.username = username;
		this.authority = authority;
	}
	
	
}
