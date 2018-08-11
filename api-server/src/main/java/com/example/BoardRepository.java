package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BoardRepository extends PagingAndSortingRepository <Board, String> {
	@Modifying
	@Transactional
	@Query(value = "UPDATE Board SET good=?2 WHERE id=?1")
	void updateCount(int id,int good);
	
	List<Board> findByTitleContaining(String title);
}

