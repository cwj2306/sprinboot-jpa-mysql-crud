package com.cos.crud.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.crud.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

	// select : @Query 만 써도 됨
	// update : @Transactional + @Modifying
	// insert, delete : @Transactional
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE board SET count=count+1 WHERE id=?1", nativeQuery = true)
	void increaseCount(int id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE board SET createDate=now() WHERE id=?1", nativeQuery = true)
	void timeUpdate(int id);
}
