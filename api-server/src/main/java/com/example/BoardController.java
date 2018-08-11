package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	GoodRepository goodrepository;

	@RequestMapping(value = "/boards", method = RequestMethod.GET)
	@ResponseBody
	public List<Board> findAllBoards(Sort sort) {
		ArrayList<Board> ar = new ArrayList<>();
		boardrepository.findAll(sort).forEach(e -> ar.add(e));
		return ar;
	}

	@RequestMapping(value = "/boards", method = RequestMethod.POST)
	@ResponseBody
	public Board insertBoards(@RequestBody Board board) {
		return boardrepository.save(board);
	}

	@RequestMapping(value = "/boards/search", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Board>> findBoard(@RequestParam(required = false) String title,
			@RequestParam(required = false) Integer id) {
		if (title != null && id == null) {
			return new ResponseEntity<List<Board>>(boardrepository.findByTitleContaining(title), HttpStatus.OK);
		} else if (id != null && title == null) {
			ArrayList<Board> ar = new ArrayList<>();
			ar.add(boardrepository.findOne(id));
			return new ResponseEntity<List<Board>>(ar, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/{boardid}/good", method = RequestMethod.GET)
	@ResponseBody
	public void patch(@PathVariable int boardid, @RequestParam int body) {
		boardrepository.updateCount(boardid, body);
	}

	@RequestMapping(value = "/boards/good", method = RequestMethod.POST)
	@ResponseBody
	public HttpStatus plusGood(@RequestBody Good good) {
		boolean check = false;
		Iterator<Good> it = goodrepository.findAll().iterator();
		while (it.hasNext()) {
			Good a = it.next();
			if (a.getBoardid() == good.getBoardid() && a.getMemberid().equals(good.getMemberid())) {
				check = true;
			}
		}
		check=false;
		if (check) {
			return HttpStatus.BAD_REQUEST;
		} else {
			int good2 = boardrepository.findOne(good.boardid).getGood();
			boardrepository.updateCount(good.boardid, good2 + 1);
			goodrepository.save(good);
			return HttpStatus.OK;
		}
	}
}
