package com.example;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Member {
	@Id
	String id;
	String email;
	String password;
	String username;

	public Member() {
	}

	public Member(String id, String email, String password, String username) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.username = username;
	}

}