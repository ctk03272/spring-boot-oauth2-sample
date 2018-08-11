package com.example;

import lombok.Data;

@Data
public class LoginVo {
	String id;
	String password;

	public LoginVo(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
}
