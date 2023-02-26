package com.aaron.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 스프링이 com.aaron.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 new하는 것이 아니라
 * 특정 어노테이션(Controller, Service, Mapper, Component 등)이 붙어있는 클래스 파일들을 new해서(IoC; 제어의 역전)
 * 스프링 컨테이너에 넣어 관리해줍니다.
 */
@RestController
public class BlogControllerTest {
	
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
}
