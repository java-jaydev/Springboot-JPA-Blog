package com.aaron.blog;

import org.junit.Test;

import com.aaron.blog.model.Reply;

public class ReplyObjectTest {

	@Test
	public void toStringTest() {
		Reply reply = Reply
				.id(1)
				.user(null)
				.board(null)
				.content("안녕")
				.builder();
		
		System.out.println(reply);
	}
}
