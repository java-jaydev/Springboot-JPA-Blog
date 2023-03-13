package com.aaron.blog.test;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.blog.model.RoleType;
import com.aaron.blog.model.User;
import com.aaron.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired // DI; 의존성 주입
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable Long id) {
		try {
			userRepository.deleteById(id);	
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id(" + id + ")는 DB에 없습니다.";
		}
		
		return "삭제되었습니다. id : " + id;
	}
	
	// save함수는 id를 전달하지 않으면 insert를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고 없으면 insert를 해줌.
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User requestUser) { // json 데이터를 요청 => Java Object (MessageConverter의 Jackson 라이브러리가 변환해서 받아줌)
		System.out.println("id: " + id);
		System.out.println("password: " + requestUser.getPassword());
		System.out.println("email: " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패했습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		user.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		
		// userRepository.save(user);
		
		// 더티 체킹
		return user;
	}

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
