package com.example.board.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	

}