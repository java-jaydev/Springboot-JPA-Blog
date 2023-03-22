package com.aaron.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    
}
