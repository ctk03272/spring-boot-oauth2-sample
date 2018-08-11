package com.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GoodRepository extends PagingAndSortingRepository<Good, Integer> {

}
