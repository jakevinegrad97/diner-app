package com.vinegrad.dosecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="users")
@NoArgsConstructor
//@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long userId;
	@Column(name="username")
	@Size(min=2,max=12,message="{username.size}")
	@Pattern(regexp="^[A-Za-z_0-9]+$",message="{username.pattern}")
	private String username;
	@Column(name="password")
	@Size(min=5,max=100,message="{password.size}")
	private String password;
	@Column(name="enabled")
	private boolean enabled;
	private String oldPassword;
	private String newPassword;
	
	public User(String username, String password, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
	
	
}
