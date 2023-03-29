package com.aaron.blog.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.blog.dto.ResponseDto;
import com.aaron.blog.model.RoleType;
import com.aaron.blog.model.User;
import com.aaron.blog.service.UserService;

@RestController
public class UserApiController {

	public final String TAG = "UserApiController: ";
	Logger logger = LoggerFactory.getLogger(UserApiController.class);

	@Autowired
	private UserService userService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		logger.info(TAG + "save 호출됨");
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) {
		logger.info(TAG + "update 호출됨");
		userService.회원수정(user);
		// 여기서 트랜잭션이 끝나고 DB값이 변경됨
		// 하지만 세션값은 변경되지 않음, 직접 변경해줘야함
		
		// 세션 등록

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
