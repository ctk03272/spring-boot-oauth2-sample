package com.example;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class AccesToken {
	@Id
	String access_token;
	String token_type;
	String refresh_token;
	String expires_in;
	String scope;
	String jti;

	public AccesToken(String access_token, String token_type, String refresh_token, String expires_in, String scope,
			String jti) {
		super();
		this.access_token = access_token;
		this.token_type = token_type;
		this.refresh_token = refresh_token;
		this.expires_in = expires_in;
		this.scope = scope;
		this.jti = jti;
	}

	public AccesToken() {
		super();
	}

}
