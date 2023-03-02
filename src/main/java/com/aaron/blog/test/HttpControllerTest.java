package com.aaron.blog.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 사용자가 요청(request) -> 응답(Data)
 **/
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	Logger logger = LoggerFactory.getLogger(HttpControllerTest.class);
	
	// 인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.
	// http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member member) {
		logger.info(TAG + member.getId());
		logger.info("===" + member.getId() + "===" + member.getUsername() + "===" + member.getPassword() + "===" + member.getEmail());
		return "get 요청";
	}
	
	// http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member member) { // MessageConverter (스프링부트)
		logger.info("===" + member.getId() + "===" + member.getUsername() + "===" + member.getPassword() + "===" + member.getEmail());
		return "post 요청";
	}
	
	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member member) {
		logger.info("===" + member.getId() + "===" + member.getUsername() + "===" + member.getPassword() + "===" + member.getEmail());
		return "put 요청";
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest(@PathVariable int id) {
		return "delete 요청";
	}
	
}
