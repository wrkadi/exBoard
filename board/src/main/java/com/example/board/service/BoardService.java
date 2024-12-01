package com.example.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.dto.*;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
//import javax.transaction.Transactional;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {	// 글쓰기
        return boardRepository.save(boardDto.toEntity()).getId();
    }
    @Transactional
    public List<BoardDto> getBoardList() {		// 게시판 목록 가져오기
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }
    
    @Transactional
    public BoardDto getPost(Long id) {
    	Board board = boardRepository.findById(id).get();
    	
        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .build();  	
        
    	return boardDto;
    }
    
    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }
}
