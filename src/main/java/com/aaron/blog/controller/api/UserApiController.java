package com.aaron.blog.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		logger.info(TAG + "save 호출됨");
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
}
