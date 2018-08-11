package com.example;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BoardRepository extends PagingAndSortingRepository <Board, String> {
}
