package com.aaron.blog;

import org.junit.Test;

import com.aaron.blog.model.Reply;

public class ReplyObjectTest {

	@Test
	public void toStringTest() {
		Reply reply = Reply.builder()
				.id(1L)
				.user(null)
				.board(null)
				.content("안녕")
				.build();
		
		System.out.println(reply);
	}
}
