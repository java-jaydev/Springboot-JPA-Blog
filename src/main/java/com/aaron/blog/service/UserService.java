package com.aaron.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aaron.blog.model.User;
import com.aaron.blog.repository.UserRepository;



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

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
    }
    
}

/*
 * @Transactional(readOnly = true) // SELECT할 때(서비스 시작 시) 트랜잭션 시작, 서비스 종료 시 트랜잭션
 * 종료 때 까지 정합성 유지 public User 로그인(User user) { return
 * userRepository.findByUsernameAndPassword(user.getUsername(),
 * user.getPassword()); }
 */
