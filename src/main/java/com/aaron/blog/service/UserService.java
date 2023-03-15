package com.aaron.blog.service;

import com.aaron.blog.model.User;
import com.aaron.blog.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/*
* 1. 트랜잭션 관리
*   - 레이어(Controller - Service - Dao)
* 2. 서비스의 의미
* */
@Service
public class UserService {

    public final String TAG = "UserService: ";
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(User user) {
        userRepository.save(user);
    }
}
