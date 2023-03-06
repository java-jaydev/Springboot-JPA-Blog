package com.aaron.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.blog.model.RoleType;
import com.aaron.blog.model.User;
import com.aaron.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired // DI; 의존성 주입
	private UserRepository userRepository;

	// {id} 주소로 파라미터를 전달 받을 수 있습니다.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable Long id) {
		// null이 반환될 경우
		// Optional로 너의 User 객체를 감싸서 가져올테니 null 인지 아닌지 판단해서 return 해
		// User user = userRepository.findById(id).get();
		/*
		 * User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
		 * 
		 * @Override public User get() { // TODO Auto-generated method stub return new
		 * User(); } });
		 */
		/*
		 * User user = userRepository.findById(id).orElseThrow(new
		 * Supplier<IllegalArgumentException>() {
		 * 
		 * @Override public IllegalArgumentException get() { return new
		 * IllegalArgumentException("해당 유저는 없습니다. id : " + id); } });
		 */
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
		});
		// 요청은 웹브라우저가 함
		// user 객체는 자바 오브젝트
		// 웹브라우저가 이해할수 있는 데이터로 변환 해야함(json) (Gson 라이브러리)
		// 스프링부트는 MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// User 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.(리턴)
		return user;
	}

	// http://localhost:8080/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("username2 : " + user.getUsername());
		System.out.println("password2 : " + user.getPassword());
		System.out.println("email2 : " + user.getEmail());
		
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
