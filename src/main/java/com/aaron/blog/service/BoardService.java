package com.aaron.blog.service;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aaron.blog.model.Board;
import com.aaron.blog.model.User;
import com.aaron.blog.repository.BoardRepository;

@Service
public class BoardService {

    public final String TAG = "BoardService: ";
    Logger logger = LoggerFactory.getLogger(BoardService.class);

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(Board board, User user) {
    	board.setCount(0);
    	board.setUser(user);
    	boardRepository.save(board);
    }
    
    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable) {
    	return boardRepository.findAll(pageable);
    }
    
    @Transactional(readOnly = true)
    public Board 글상세보기(Long id) {
    	return boardRepository.findById(id).orElseThrow(()->{
    		return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
    	});
    }
    
    @Transactional
    public void 삭제하기(Long id) {
    	boardRepository.deleteById(id);
    }
    
    @Transactional
    public void 수정하기(Long id, Board requestBoard) {
    	Board board = boardRepository.findById(id).orElseThrow(()->{
    		return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
    	});
    	
    	board.setTitle(requestBoard.getTitle());
    	board.setContent(requestBoard.getContent());
    	board.setUpdateDate(new Timestamp(System.currentTimeMillis()));
    }
}
