package com.aaron.blog.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.blog.dto.ResponseDto;
import com.aaron.blog.model.User;

@RestController
public class UserApiController {
	
	public final String TAG = "UserApiController: ";
	Logger logger = LoggerFactory.getLogger(UserApiController.class);

	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		logger.info(TAG + "save 호출됨");
		// 실제로 DB에 insert를 하고 아래에서 return을 함
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
}
