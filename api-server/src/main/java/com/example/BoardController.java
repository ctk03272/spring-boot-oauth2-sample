package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
	@Autowired
	BoardRepository boardrepository;

	@RequestMapping(value = "/boards", method = RequestMethod.GET)
	@ResponseBody
	public List<Board> findAllBoards(Sort sort) {
		ArrayList<Board> ar = new ArrayList<>();
		boardrepository.findAll(sort).forEach(e -> ar.add(e));
		return ar;
	}
	
	@RequestMapping(value = "/{boardid}/good", method = RequestMethod.PATCH)
	@ResponseBody
	public void patch(@PathVariable int boardid,@RequestParam int body) {
		boardrepository.updateCount(boardid, body);
	}
}
