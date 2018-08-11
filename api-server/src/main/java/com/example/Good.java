package com.example;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Good {
	@Id
	int boardid;
	String memberid;
	
	

	public Good(int boardid, String memberid) {
		super();
		this.boardid = boardid;
		this.memberid = memberid;
	}


	public Good() {
		super();
	}

}
