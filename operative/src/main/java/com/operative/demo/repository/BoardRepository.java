package com.operative.demo.repository;

import org.springframework.stereotype.Repository;

import com.operative.demo.model.Board;

@Repository
public class BoardRepository extends GenericRepository<Board, String> {

	@Override
	public Class<Board> getEntityClass() {
		return Board.class;
	}

}
