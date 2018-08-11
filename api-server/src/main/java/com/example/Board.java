package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Board {

	@Id
	@GeneratedValue
	int id;
	String memberid;
	String content;
	String title;
	String pictureurl;
	int good;

	public Board() {
		super();
	}

	public Board(int id, String memberid, String content, String title, String pictureurl, int good) {
		super();
		this.id = id;
		this.memberid = memberid;
		this.content = content;
		this.title = title;
		this.pictureurl = pictureurl;
		this.good = good;
	}

}
